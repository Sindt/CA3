/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sindt
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Currency.findAll", query = "SELECT c FROM Currency c"),
    @NamedQuery(name = "Currency.findByCode", query = "SELECT c FROM Currency c WHERE c.code = :code")})
@Table(name = "currency")
public class Currency implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "currency_id")
    private int id;
    @Column(name = "country_code")
    private String code;
    @Column(name = "description")
    private String desc;
    @Column(name = "rateOld")
    private double rateOld;
    @Column(name = "rateNew")
    private double rateNew;

    public Currency() {
    }

    public Currency(int id, String code, String desc, double rate) {
        this.id = id;
        this.code = code;
        this.desc = desc;
        this.rateOld = rate;
    }
    
    

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getRateOld() {
        return rateOld;
    }

    public void setRateOld(double rateOld) {
        this.rateOld = rateOld;
    }

    public double getRateNew() {
        return rateNew;
    }

    public void setRateNew(double rateNew) {
        this.rateNew = rateNew;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
