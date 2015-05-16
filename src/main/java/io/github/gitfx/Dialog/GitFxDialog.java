/**
 * Copyright 2015 GitFx
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package io.github.gitfx.Dialog;

import io.github.gitfx.GitFxApp;
import io.github.gitfx.GitResourceBundle;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.util.Duration;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Concrete class which does GitDialog implementation
 *
 * @author rvvaidya
 */
public class GitFxDialog implements GitDialog {

    private GitFxDialogResponse response;
    private String tempResponse;
    private GitResourceBundle resourceBundle;
    Logger logger = LoggerFactory.getLogger(GitFxApp.class.getName());

    public GitFxDialog() {
        resourceBundle = new GitResourceBundle();
    }
    /*
     *Implementaiton of Generic Information dialog
     */

    @Override
    public Dialog GitInformationDialog(String title, String header, String label) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(label);
        alert.showAndWait();
        return alert;
    }

    /*
     *Implementation of Generic Error dialog
     */
    @Override
    public void GitErrorDialog(String title, String error, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(error);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /*
     * Implementation of Generic Warning Dialog
     */
    @Override
    public void GitWarningDialog(String title, String header, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /*
     * Implementation of Generic Exception Dialog
     */
    @Override
    public void GitExceptionDialog(String title, String header, String content, Exception e) {

        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String exceptionText = sw.toString();
        Label label = new Label("Exception stack trace:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);
        alert.getDialogPane().setExpandableContent(expContent);
        alert.showAndWait();
    }
    /*
     * Implementation of Git Open Dialog. 
     */

    @Override
    public String GitOpenDialog(String title, String header, String content) {
        String repo;
        DirectoryChooser chooser = new DirectoryChooser();
        Dialog<Pair<String, GitFxDialogResponse>> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);

        ButtonType chooseButtonType = new ButtonType("Choose");
        ButtonType okButton = new ButtonType("Ok");
        ButtonType cancelButton = new ButtonType("Canel");

        dialog.getDialogPane().getButtonTypes().addAll(chooseButtonType, okButton, cancelButton);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField repository = new TextField();
        repository.setPromptText("Repo");
        grid.add(new Label("Repository:"), 0, 0);
        grid.add(repository, 1, 0);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == chooseButtonType) {
                File path = chooser.showDialog(dialog.getOwner());
                if (path != null) {
                    repository.setText(path.getPath());
                }
                chooser.setTitle(resourceBundle.getString("repo"));
                setResponse(GitFxDialogResponse.CHOOSE);
                return null;
            }
            if (dialogButton == okButton) {
                String filePath = repository.getText();
                filePath = filePath.concat("/.git");
                logger.debug(filePath);
                if (!filePath.isEmpty() && new File(filePath).exists()) {
                    setResponse(GitFxDialogResponse.OK);
                    return new Pair<>(repository.getText(), GitFxDialogResponse.OK);
                } else {
                    this.GitErrorDialog(resourceBundle.getString("errorOpen"),
                            resourceBundle.getString("errorOpenTitle"),
                            resourceBundle.getString("errorOpenDesc"));
                }

            }
            if (dialogButton == cancelButton) {
                logger.debug("Cancel clicked");
                setResponse(GitFxDialogResponse.CANCEL);
                return new Pair<>(null, GitFxDialogResponse.CANCEL);
            }
            return null;
        });

        Optional<Pair<String, GitFxDialogResponse>> result = dialog.showAndWait();

        result.ifPresent(repoPath -> {
            logger.debug(repoPath.getKey() + " " + repoPath.getValue().toString());
        });

        Pair<String, GitFxDialogResponse> temp = null;
        if (result.isPresent()) {
            temp = result.get();
        }
        return temp.getKey() + "/.git";
    }

    /*
     * Implementation of Git Open Dialog. 
     */
    @Override
    public Pair<String, String> GitInitDialog(String title, String header, String content) {
        Pair<String, String> repo;
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        dialog.setContentText(content);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        TextField localPath = new TextField();
        localPath.setPromptText("Local Path");
        grid.add(new Label("Local Path:"), 0, 0);
        grid.add(localPath, 1,0);
        ButtonType chooseButtonType = new ButtonType("Choose");
        ButtonType initRepo = new ButtonType("Initialize Repository");
        ButtonType cancelButtonType = new ButtonType("Cancel");
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(chooseButtonType, initRepo, cancelButtonType);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == chooseButtonType) {
                DirectoryChooser chooser = new DirectoryChooser();
                chooser.setTitle(resourceBundle.getString("selectRepo"));
                File initialRepo = chooser.showDialog(dialog.getOwner());
                localPath.setText(initialRepo.getPath());
                dialog.getDialogPane();
                setResponse(GitFxDialogResponse.CHOOSE);
                return null;
            }
            if (dialogButton == initRepo) {
                setResponse(GitFxDialogResponse.INITIALIZE);
                String path = localPath.getText();
                logger.debug( "path" + path + path.isEmpty());
                if (new File(path).exists()) {
                   logger.debug(path.substring(localPath.getText().lastIndexOf(File.separator)));
                   String projectName= path.substring(localPath.getText().lastIndexOf(File.separator)+1);
                    return new Pair<>(projectName, localPath.getText());
                } else {
                    this.GitErrorDialog(resourceBundle.getString("errorInit"),
                            resourceBundle.getString("errorInitTitle"),
                            resourceBundle.getString("errorInitDesc"));
                }
            }
            if (dialogButton == cancelButtonType) {
                setResponse(GitFxDialogResponse.CANCEL);
                return new Pair<>(null, null);
            }
            return null;
        });
        Optional<Pair<String, String>> result = dialog.showAndWait();
        Pair<String, String> temp = null;
        if (result.isPresent()) {
            temp = result.get();
        }
        return temp;
    }

    @Override
    public Pair<String, String> GitCloneDialog(String title, String header,
            String content) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        DirectoryChooser chooser = new DirectoryChooser();
        dialog.setTitle(title);
        dialog.setHeaderText(header);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
        TextField remoteRepo = new TextField();
        remoteRepo.setPromptText("Remote Repo URL");
        TextField localPath = new TextField();
        localPath.setPromptText("Local Path");
        grid.add(new Label("Remote Repo URL:"), 0, 0);
        grid.add(remoteRepo, 1, 0);
        grid.add(new Label("Local Path:"), 0, 1);
        grid.add(localPath, 1, 1);
        ButtonType chooseButtonType = new ButtonType("Choose");
        ButtonType cloneButtonType = new ButtonType("Clone");
        ButtonType cancelButtonType = new ButtonType("Cancel");
        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(chooseButtonType,
                cloneButtonType, cancelButtonType);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == chooseButtonType) {
                logger.debug("Choose clicked");
                chooser.setTitle(resourceBundle.getString("cloneRepo"));
                File cloneRepo = chooser.showDialog(dialog.getOwner());
                localPath.setText(cloneRepo.getPath());
                setResponse(GitFxDialogResponse.CHOOSE);
                return null;
            }
            if (dialogButton == cloneButtonType) {
                setResponse(GitFxDialogResponse.CLONE);
                String remoteRepoURL = remoteRepo.getText();
                String localRepoPath = localPath.getText();
                if (!remoteRepoURL.isEmpty() && !localRepoPath.isEmpty()) {
                    return new Pair<>(remoteRepo.getText(), localPath.getText());
                } else {
                    this.GitErrorDialog(resourceBundle.getString("errorClone"),
                            resourceBundle.getString("errorCloneTitle"),
                            resourceBundle.getString("errorCloneDesc"));
                }
            }
            if (dialogButton == cancelButtonType) {
                logger.debug("Cancel clicked");
                setResponse(GitFxDialogResponse.CANCEL);
                return new Pair<>(null, null);
            }
            return null;
        });
        Optional<Pair<String, String>> result = dialog.showAndWait();
        Pair<String, String> temp = null;
        if (result.isPresent()) {
            temp = result.get();
        }
        return temp;
    }

    /*
     * Apply fade transition to dialog
     */
    public void applyFadeTransition(Dialog node) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.seconds(.5));
        fadeTransition.setNode(node.getDialogPane());
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();
    }

    public void GitConfirmationDialog(String title, String header, String content) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            setResponse(GitFxDialogResponse.OK);
        } else {
            setResponse(GitFxDialogResponse.CANCEL);
        }
    }

    /**
     *
     * @return response
     */
    @Override
    public GitFxDialogResponse getResponse() {
        return response;
    }

    public void setResponse(GitFxDialogResponse response) {
        this.response = response;
    }

}
