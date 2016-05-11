function Stage() {
	this.gc=[];
	this.rc = [];
	this.mc = [];
	this.ttt = []
	this.stage = [];
	this.m1 = [];
	this.m2 = [];
	this.m3 = [];
	this.m4 = [];
	this.mhc = [];
	this.sInt = [];
	this.id = 0;
}
Stage.prototype.getId = function() {
    return this.id;
}
Stage.prototype.setId = function(id){
    this.id = id;
}
Stage.prototype.getGC = function() {
    return this.gc;
}

Stage.prototype.setGC = function(array) {
    if (array.constructor === Array) {
        this.gc = array;
    } else {
        alert("This is not an array");
    }
}
Stage.prototype.getRC = function() {
    return this.rc;
}
Stage.prototype.setRC = function(array) {
    if (array.constructor === Array) {
        this.rc = array;
    } else {
        alert("This is not an array");
    }
}
Stage.prototype.getMC = function() {
    return this.mc;
}
Stage.prototype.setMC = function(array) {
    if (array.constructor === Array) {
        this.mc = array;
    } else {
        alert("This is not an array");
    }
}
Stage.prototype.getTTT = function() {
    return this.ttt;
}
Stage.prototype.setTTT = function(array) {
    if (array.constructor === Array) {
        this.ttt = array;
    } else {
        alert("This is not an array");
    }
}
Stage.prototype.getStage = function() {
    return this.stage;
}
Stage.prototype.setStage = function(array) {
    if (array.constructor === Array) {
        this.stage = array;
    } else {
        alert("This is not an array");
    }
}
Stage.prototype.setM1 = function(array) {
    if (array.constructor === Array) {
        this.m1 = array;
    } else {
        alert("This is not an array");
    }
}
Stage.prototype.getM1 = function() {
    return this.m1;
}
Stage.prototype.setM2 = function(array) {
    if (array.constructor === Array) {
        this.m2 = array;
    } else {
        alert("This is not an array");
    }
}
Stage.prototype.getM2 = function() {
    return this.m2;
}
Stage.prototype.setM3 = function(array) {
    if (array.constructor === Array) {
        this.m3 = array;
    } else {
        alert("This is not an array");
    }
}
Stage.prototype.getM3 = function() {
    return this.m3;
}
Stage.prototype.setM4 = function(array) {
    if (array.constructor === Array) {
        this.m4 = array;
    } else {
        alert("This is not an array");
    }
}
Stage.prototype.getM4 = function() {
    return this.m4;
}
Stage.prototype.setMHC = function(array) {
    if (array.constructor === Array) {
        this.mhc = array;
    } else {
        alert("This is not an array");
    }
}
Stage.prototype.getMHC = function() {
    return this.mhc;
}
Stage.prototype.setSInt = function(array) {
    if (array.constructor === Array) {
        this.sInt = array;
    } else {
        alert("This is not an array");
    }
}
Stage.prototype.getSInt = function() {
    return this.sInt;
}

//Helpers

Stage.prototype.addSInt = function(sInt) {
    this.sInt.push(sInt);
}
Stage.prototype.addM1 = function(m1){
    this.m1.push(m1);
}
Stage.prototype.addM2 = function(m2) {
    this.m2.push(m2);
}
Stage.prototype.addM3 = function(m3) {
    this.m3.push(m3);
}
Stage.prototype.addM4 = function(m4) {
    this.m4.push(m4);
}
Stage.prototype.addMHC = function(mhc) {
    this.mhc.push(mhc);
}
Stage.prototype.toJSON = function() {
    var json = {};
    json.id = this.id;
    if (this.gc.length > 0) {
        json.gc = this.gc;
    }
    if (this.rc.length > 0) {
        json.rc = this.rc;
    }
    if (this.mc.length > 0) {
        json.mc = this.mc;
    }
    if (this.ttt.length > 0) {
        json.ttt = this.ttt;
    }
    if (this.stage.length>0) {
        json.stage = this.stage;
    }
    if (this.m1.length > 0) {
        json.m1 = this.m1;
    }
    if (this.m2.length > 0) {
        json.m2 = this.m2;
    }
    if (this.m3.length > 0) {
        json.m3 = this.m3;
    }
    if (this.m4.length > 0) {
        json.m4 = this.m4;
    }
    if (this.mhc.length > 0) {
        json.mhc = this.mhc;
    }
    if (this.sInt.length > 0) {
        json.sInt = this.sInt;
    }
    return json;
}