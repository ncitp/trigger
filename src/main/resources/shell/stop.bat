@echo off

setlocal enabledelayedexpansion
for /f "tokens=1" %%a in ('jps ^| findstr trigger.jar') do taskkill /f /pid %%a

exit