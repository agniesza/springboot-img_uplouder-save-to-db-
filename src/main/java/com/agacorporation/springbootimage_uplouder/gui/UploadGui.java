package com.agacorporation.springbootimage_uplouder.gui;


import com.agacorporation.springbootimage_uplouder.ImageUploader;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;


@Route("uploadImage")
public class UploadGui extends VerticalLayout {
private ImageUploader imageUploader;

@Autowired
    public  UploadGui(ImageUploader imageUploader){
         this.imageUploader=imageUploader;

        TextField textField=new TextField();
        Button button=new Button(("upload"));
         Label label=new Label();

        button.addClickListener(clickEvent->
        {

            String img=imageUploader.uploadFile(textField.getValue());
            Image image=new Image(img, "there is no image");
            label.setText("file upload successfully");
            add(label);
            add(image);
        });

        add(textField);
        add(button);
    }
}
