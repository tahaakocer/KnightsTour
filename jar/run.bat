@echo off
start "" /B javaw --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml -jar KnightsTour.jar
