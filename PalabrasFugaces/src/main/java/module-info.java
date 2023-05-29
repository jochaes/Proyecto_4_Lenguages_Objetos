module proyecto.palabrasfugaces {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens proyecto.palabrasfugaces to javafx.fxml;
    exports proyecto.palabrasfugaces;
}