/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tourismproject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author fefoss454
 */
public class PageManager {
    
    private final StringProperty page = new SimpleStringProperty();
    
    public StringProperty pageProperty() {
        return page;
    }
    
    public final String getPage() {
        return pageProperty().get();
    }
    
    public final void setPage(String page) {
        pageProperty().set(page);
    }
}
