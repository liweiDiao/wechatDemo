package com.dlw.common.core;

import java.io.Serializable;


public interface SuperModel<ID extends Serializable> {

    abstract ID getId();

    abstract void setId(ID id);

    abstract void resetModifyState();

    abstract String[] getIgnoreProperties();
}
