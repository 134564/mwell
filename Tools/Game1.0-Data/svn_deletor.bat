@echo On
@Rem ɾ��SVN�汾����Ŀ¼
@PROMPT [Com]#

@for /r . %%a in (.) do @if exist "%%a\.SVN" rd /s /q "%%a\.SVN"
@Rem for /r . %%a in (.) do @if exist "%%a\.SVN" @echo "%%a\.SVN"

@echo Mission Completed.
@pause