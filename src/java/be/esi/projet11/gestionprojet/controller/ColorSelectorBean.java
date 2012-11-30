/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.esi.projet11.gestionprojet.controller;

import be.esi.projet11.gestionprojet.enumeration.ImportanceEnum;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author g35001
 */
@ManagedBean(name = "colorCtrl")
@ApplicationScoped
public class ColorSelectorBean {

    public String computeColor(ImportanceEnum importance) {
        if (importance == null) {
            return "";
        }
        switch (importance.getImp()) {
            case 1 : return "background-color: #ff0000;";
            case 2 : return "background-color:#ffcc00;";
            case 3 : return "background-color: #0099ff;";
            default : return "";
        }
    }
}