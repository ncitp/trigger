-- ================================================
-- Template generated from Template Explorer using:
-- Create Procedure (New Menu).SQL
--
-- Use the Specify Values for Template Parameters 
-- command (Ctrl-Shift-M) to fill in the parameter 
-- values below.
--
-- This block of comments will not be included in
-- the definition of the procedure.
-- ================================================
use test
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
if(exists (select * from sys.objects where name = 'proc_pushAPI'))
	drop proc proc_pushAPI
go

CREATE procedure [dbo].[proc_pushAPI]
	@params varchar(max)
AS
BEGIN
	
	print @params

	-- �ӿ�·��
	declare @url varchar(max)
	-- OLE����ʵ��
	declare @object int
	-- �ı�
	declare @responseText varchar(max)

	set @url = 'http://192.168.0.3:8000/api/v1/notice/pull?params=' + @params

	print @url

	exec sp_OACreate'MSXML2.XMLHTTP',@object out
	exec sp_OAMethod @object,'open',null,'get',@url,'false'
	exec sp_OAMethod @object,'send'
	exec sp_OAMethod @object,'responseText',@responseText output

	print @responseText

	exec sp_OADestroy @object

	SET NOCOUNT ON;

END
GO
