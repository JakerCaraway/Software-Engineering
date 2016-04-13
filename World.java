/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AntClass;

/**
 *
 * @author mjc53
 */
class World {

    static String getOtherColour(String colour) {
        if (colour == "red"){
            return "black";
        }else if (colour == "black") {
            return "red";
        } else return "none";
    }
    
}
