function Person(name) {
this.name = name;
}

Person.prototype.greet = function() {
return "Hello, " + this.name;
};


var f={
"hola":"hola juan",

Person:Person

};

var person=new f.Person("Juan");

