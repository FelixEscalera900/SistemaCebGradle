/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaceb.form;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author escal
 */
public class DateInput extends FormElement{
    
    limitedImput dia;
    limitedImput mes;
    limitedImput a�o;
    
    public DateInput(String title){
        super(title);
        addWrongTypeError();
        add(buildInputsSec(),BorderLayout.CENTER);
        currentElement = this;
    }

    @Override
    public void setResponse(String txt) {

        ArrayList<String> trimedDate = trimDate(txt);
        dia.setText(trimedDate.get(2));
        mes.setText(trimedDate.get(1));
        a�o.setText(trimedDate.get(0));
    }

    private ArrayList<String> trimDate(String txt){
        ArrayList<String> values = new ArrayList<>();
        String currentWord = "";
        for(int i = 0;i < txt.length();i++){
            char currentChar = txt.charAt(i);
            if(currentChar == '-'){
                values.add(currentWord);
                currentWord = "";
            } else
                currentWord+=currentChar;

        }
        values.add(currentWord);

        String year = values.get(0);
        String newYear = "";
        for(int i = 2;i < year.length();i++){
            newYear+= year.charAt(i);
        }
        values.set(0,newYear);
        return values;
    }

    private JPanel buildInputsSec(){
        JPanel parentCont = new JPanel(new GridLayout(1,3));
        
        dia = new limitedImput().setLimit(2);
        mes = new limitedImput().setLimit(2);
        a�o = new limitedImput().setLimit(2);
        
        dia.setText("DD");
        mes.setText("MM");
        a�o.setText("AA");

        parentCont.add(dia);
        parentCont.add(mes);
        parentCont.add(a�o);
        
        addCloseOnClick(dia);
        addCloseOnClick(mes);
        addCloseOnClick(a�o);
        

        
        return parentCont;
    }


    @Override
    protected String getResponseConfig(){
        if (isEmpty())
            return "";

        return "20" + a�o.getText() + "-" + mes.getText() + "-" + dia.getText();

    }

    private boolean isEmpty(){
        String ano = a�o.getText();
        String me = mes.getText();
        String di = dia.getText();

        boolean isDef = ano.equals("AA") ||
                me.equals("MM") ||
                di.equals("DD");

        boolean isEmpty = ano.isEmpty() || di.isEmpty() || me.isEmpty();

        return  isEmpty || isDef;
    }

    private void addWrongTypeError(){
        this.addErrorChecker(new ErrorChecker(){
            
            @Override
            public String checkForError(String response) {
                if(Integer.parseInt(mes.getText()) > 12 ||
                        Integer.parseInt(dia.getText()) >30)
                    return "Error en el Formato de la Fecha";

                return "";
                
            }
        });
    }




    private void addCloseOnClick(JTextField field){
        
        MouseAdapter closeOnClick = new MouseAdapter(){
            
            @Override
            public void mouseClicked(MouseEvent e){
                field.setText("");
            }
        };
        
        field.addMouseListener(closeOnClick);
    }

    
}
