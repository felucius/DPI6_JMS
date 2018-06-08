/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jmsmessaging;

import domain.ReservationReply;
import domain.ReservationRequest;

/**
 *
 * @author M
 */
public class ReservationValuePair {

    private ReservationRequest request;
    private ReservationReply reply;

    public ReservationValuePair() {

    }

    public ReservationValuePair(ReservationRequest request, ReservationReply reply) {
        setRequest(request);
        setReply(reply);
    }

    public ReservationRequest getRequest() {
        return request;
    }

    public void setRequest(ReservationRequest request) {
        this.request = request;
    }

    public ReservationReply getReply() {
        return reply;
    }

    public void setReply(ReservationReply reply) {
        this.reply = reply;
    }
}
