package com.fuyi.shop.rpc.entity;

import java.io.Serializable;
import java.util.Date;

public class ShopSpuDesc implements Serializable {
    private Integer spuId;

    private Date created;

    private Date updated;

    private byte[] spuDesc;

    public Integer getSpuId() {
        return spuId;
    }

    public void setSpuId(Integer spuId) {
        this.spuId = spuId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public byte[] getSpuDesc() {
        return spuDesc;
    }

    public void setSpuDesc(byte[] spuDesc) {
        this.spuDesc = spuDesc;
    }
}