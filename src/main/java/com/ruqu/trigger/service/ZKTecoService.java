package com.ruqu.trigger.service;

import cn.hutool.core.date.DateBetween;
import cn.hutool.core.date.DateTime;
import cn.hutool.http.HttpUtil;
import com.ruqu.trigger.entity.ConfigEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.HashMap;

/**
 * @Project TriggerTools
 * @Package com.ruqu.trigger.service
 * @Author ncitp
 * @Date 2020 12 2020-12-30 21:00
 * @Description 设备Service
 */
@Slf4j
@Service
public class ZKTecoService {

    @Resource
    ConfigService configService;

    /**
     * 设备配置信息
     * @param sn 表示客户端的序列号
     * @param options 表示获取服务器配置参数，目前值只有all
     * @param pushver 表示设备当前最新的push协议版本，新开发的客户端必须支持且必须大于等于2.2.14版本
     * @return
     */
    public String init(String sn, String options, String pushver){
        log.info("设备配置信息：SN-"+sn+",options-"+options+",pushver-"+pushver);
        Date date = new Date();
        StringBuffer str = new StringBuffer();
        str.append("GET OPTION FROM: "+sn+"\n");
        str.append("ATTLOGStamp=9999\n");
        str.append("OPERLOGStamp=9999\n");
        str.append("ErrorDelay=30\n");
        str.append("Delay=10\n");
        str.append("TransTimes=00:00\n");
        str.append("TransInterval=1\n");
        str.append("TransFlag=TransData AttLog OpLog\n");
        str.append("TimeZone=8\n");
        str.append("Realtime=1\n");
        str.append("Encrypt=None");
        String res = "HTTP/1.1 200 OK\nContent-Type:text/plain\nDate: "+date+"\n"+str.toString();
        return res;
    }

    /**
     * 交换因子
     * @param sn
     * @param type
     * @param factors
     * @return
     */
    public String exchange(String sn, String type, String factors){
        log.info("交换因子。sn-"+sn+",type-"+type+",Factors-"+factors);
        String res = "HTTP/1.1 200 OK\nContent-Type: application/push;charset=UTF-8\nFactors="+factors;
        return res;
    }

    /**
     * 心跳
     * @param sn
     * @return
     */
    public String ping(String sn){
        log.info("心跳。SN-"+sn);
        Date date = new Date();
        String res = "HTTP/1.1 200 OK\nContent-Length: 2\nDate:"+date+"\nOK";
        return res;
    }

    /**
     * 发送信息
     * @param sn
     * @param table
     * @param DataRecord
     * @return
     */
    public String sendPin(String sn, String table, String DataRecord){
        log.info("上传记录：SN-"+sn+",table-"+table+",DataRecord-"+DataRecord);
        Date begin = DateTime.now();
        log.info("接收时间："+begin);
        Date date = new Date();
        // 获取记录信息
        String[] str = DataRecord.split("\t");
        String pin = str[0];
        // 获取配置信息
        ConfigEntity configEntity = configService.findConfigInfo();
        // 根据配置信息推送
        String url = configEntity.getUrl();
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("pin", pin);
        String s = null;
        switch (configEntity.getMethod()){
            case "get":
                s = HttpUtil.get(url, paramMap);
                break;
            case "post":
                s = HttpUtil.post(url, paramMap);
                break;
            case "tcp":
                Socket socket = null;
                OutputStream os = null;
                try {
                    //服务器端口号
                    int port = configEntity.getPost();
                    //创建socket连接
                    socket = new Socket(url, port);
                    //发送消息IO流
                    os = socket.getOutputStream();
                    os.write(pin.getBytes("gbk"));
                    log.info("发送成功："+ pin.getBytes("gbk").toString());
                } catch (Exception e) {
                    log.error("发送失败：", e.getMessage());
                    e.printStackTrace();
                } finally {
                    if(os != null) {
                        try {
                            os.close();
                        } catch (IOException e) {
                            log.error("关闭流失败：", e.getMessage());
                            e.printStackTrace();
                        }
                    }
                    if(socket != null) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            log.error("关闭连接失败：", e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
                break;
            default:break;
        }
        Date end = DateTime.now();
        log.info("发送完成时间："+end+"，耗时："+ DateBetween.create(begin, end));
        String res = "HTTP/1.1 200 OK\nDate:"+date+"\nContent-Type: text/plain\nConnection: close\nPragma: no-cache\nCache-Control: no-store\nOK:";
        return res;
    }
}
