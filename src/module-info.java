module comercialV2 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.desktop;
	requires org.junit.jupiter.api;
	requires org.junit.platform.suite.api;
	requires itextpdf;
	
	opens main to javafx.graphics;
	opens controllers to javafx.fxml;
	opens model to javafx.base;
}
