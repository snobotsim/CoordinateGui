package org.snobot.coordinate_gui.gen.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class PathTreeController
{
    private static final Logger sLOGGER = LogManager.getLogger(PathTreeController.class);

    @FXML
    private TreeView<String> mPathsTree;

    private String mRootPath;

    /**
     * Constructor.
     * 
     * @param aBaseDirectory
     *            Base directory
     * @param aListener
     *            The listener
     */
    public void setAddPathListener(String aBaseDirectory, Consumer<File> aListener)
    {
        ChangeListener<TreeItem<String>> selectionListener = new ChangeListener<TreeItem<String>>()
        {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> aObservable, TreeItem<String> aOldValue, TreeItem<String> aNewValue)
            {
                if (aNewValue == null)
                {
                    return;
                }

                String relativePath = aNewValue.getValue();
                aListener.accept(new File(aBaseDirectory, relativePath));
            }

        };
        mPathsTree.getSelectionModel().selectedItemProperty().addListener(selectionListener);
    }

    /**
     * Loads the paths into the tree by recursively going through aRootPath.
     * 
     * @param aRootPath
     *            The Path to crawl
     */
    public void setuPathsView(String aRootPath)
    {
        mRootPath = aRootPath;

        ContextMenu contextMenu = new ContextMenu();
        MenuItem addPathItem = new MenuItem("Add Path");
        contextMenu.getItems().add(addPathItem);

        TreeItem<String> root = new TreeItem<>("Paths");
        mPathsTree.setRoot(root);
        mPathsTree.setContextMenu(contextMenu);

        try
        {
            Files.walk(Paths.get(mRootPath)).filter(Files::isRegularFile).forEach(this::handleNewPath);
        }
        catch (IOException ex)
        {
            sLOGGER.log(Level.ERROR, "Error loading paths", ex);
        }
    }

    /**
     * Updates the tree with the new path.
     * 
     * @param aPath
     *            The path
     */
    public void handleNewPath(Path aPath)
    {
        Path pathBase = Paths.get(mRootPath);
        Path relativePath = pathBase.relativize(aPath);
        String pathName = relativePath.toString();
        TreeItem<String> item = new TreeItem<>(pathName);
        mPathsTree.getRoot().getChildren().add(item);
    }
}
