@echo On
@Rem É¾³ýSVN°æ±¾¿ØÖÆÄ¿Â¼
@PROMPT [Com]#

@for /r . %%a in (.) do @if exist "%%a\.SVN" rd /s /q "%%a\.SVN"
@Rem for /r . %%a in (.) do @if exist "%%a\.SVN" @echo "%%a\.SVN"

@echo Mission Completed.
@pause