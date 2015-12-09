package org.python.types;


public class Object implements org.python.Object {
    public java.util.Map<java.lang.String, org.python.Object> attrs;
    public org.python.types.Type.Origin origin;

    /**
     * A utility method to update the internal value of this object.
     *
     * Used by __i*__ operations to do an in-place operation.
     * On a base object, it will always fail. Subclasses should override
     * to provide the relevant assignment info.
     */
    void setValue(org.python.Object obj) {
        throw new org.python.exceptions.RuntimeError("'" + this.typeName() + "' object cannot be updated.");
    }

    public java.lang.Object toJava() {
        return this;
    }

    public java.lang.String typeName() {
        return org.Python.typeName(this.getClass());
    }

    /**
     * Construct a new object instance.
     *
     * The argument `origin` is used to describe where the object is defined -
     * Python or Java. It can also be "PLACEHOLDER" - these are transient objects
     * that exist during instantiation of other objects. As a result, they don't
     * have attributes or any of the other usual infrastructure of a Python object.
     *
     * klass is the underlying java class being represented by this object.
     * In the case of a Python object, the klass is the Java manifestation of
     * the object; when wrapping Java objects, the native class of the object is used.
     */
    protected Object(org.python.types.Type.Origin origin, java.lang.Class klass) {
        if (origin != org.python.types.Type.Origin.PLACEHOLDER) {
            this.attrs = new java.util.HashMap<java.lang.String, org.python.Object>();
            if (klass == null) {
                klass = this.getClass();
            }
            this.__new__(org.python.types.Type.pythonType(klass));
        }
    }

    public Object() {
        this(org.python.types.Type.Origin.PYTHON, null);
    }

    /**
     * Proxy Java object methods onto their Python counterparts.
     */
    public boolean equals(java.lang.Object other) {
        try {
            return ((org.python.types.Bool) this.__eq__((org.python.types.Object) other)).value;
        } catch (ClassCastException e) {
            throw new org.python.exceptions.RuntimeError("Can't compare a Python object with non-Python object.");
        }
    }

    public int compareTo(java.lang.Object other) {
        try {
            if (((org.python.types.Bool) this.__lt__((org.python.Object) other)).value) {
                return -1;
            }
            else if (((org.python.types.Bool) this.__gt__((org.python.Object) other)).value) {
                return 1;
            }
            return 0;
        } catch (ClassCastException e) {
            throw new org.python.exceptions.RuntimeError("Can't compare a Python object with non-Python object.");
        }
    }

    public java.lang.String toString() {
        return ((org.python.types.Str) this.__str__()).value;
    }

    protected void finalize() throws Throwable {
        try {
            this.__del__();
        }
        finally {
            super.finalize();
        }
    }

    /**
     * Python interface compatibility
     * Section 3.3.1 - Basic customization
     */
    @org.python.Method(
        __doc__ = "Create and return a new object.  See help(type) for accurate signature."
    )
    public org.python.Object __new__(org.python.Object klass) {
        org.python.types.Type cls = (org.python.types.Type) klass;
        this.attrs.put("__class__", cls);
        if (cls.origin == org.python.types.Type.Origin.PLACEHOLDER) {
            cls.add_reference(this);
        }
        return cls;
    }

    // public void __init__(java.util.List<org.python.Object> args, java.util.Map<java.lang.String, org.python.Object> kwargs, java.util.List<org.python.Object> default_args, java.util.Map<java.lang.String, org.python.Object> default_kwargs) {
    //     throw new org.python.exceptions.AttributeError(this, "__init__");
    // }

    @org.python.Method(
        __doc__ = "Destroy an existing object. See help(type) for accurate signature."
    )
    public void __del__() {
        throw new org.python.exceptions.AttributeError(this, "__del__");
    }

    @org.python.Method(
        __doc__ = "Return repr(self)."
    )
    public org.python.Object __repr__() {
        return new org.python.types.Str(String.format("<%s object at 0x%x>", this.typeName(), this.hashCode()));
    }

    @org.python.Method(
        __doc__ = "Return str(self)."
    )
    public org.python.Object __str__() {
        return this.__repr__();
    }

    @org.python.Method(
        __doc__ = ""
    )
    public org.python.Object __bytes__() {
        throw new org.python.exceptions.AttributeError(this, "__bytes__");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"format_string"}
    )
    public org.python.Object __format__(org.python.Object format_string) {
        throw new org.python.exceptions.NotImplementedError("'" + this.typeName() + ".__format__' has not been implemented");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __lt__(org.python.Object other) {
        throw new org.python.exceptions.NotImplementedError("'" + this.typeName() + ".__lt__' has not been implemented");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __le__(org.python.Object other) {
        throw new org.python.exceptions.NotImplementedError("'" + this.typeName() + ".__le__' has not been implemented");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __eq__(org.python.Object other) {
        return new org.python.types.Bool(System.identityHashCode(this) == System.identityHashCode(other));
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __ne__(org.python.Object other) {
        return new org.python.types.Bool(System.identityHashCode(this) != System.identityHashCode(other));
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __gt__(org.python.Object other) {
        throw new org.python.exceptions.NotImplementedError("'" + this.typeName() + ".__gt__' has not been implemented");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __ge__(org.python.Object other) {
        throw new org.python.exceptions.NotImplementedError("'" + this.typeName() + ".__ge__' has not been implemented");
    }

    @org.python.Method(
        __doc__ = ""
    )
    public org.python.Object __hash__() {
        return new org.python.types.Int(this.hashCode());
    }

    @org.python.Method(
        __doc__ = ""
    )
    public org.python.Object __bool__() {
        throw new org.python.exceptions.AttributeError(this, "__bool__");
    }

    /**
     * Section 3.3.2 - Emulating container types
     */

    @org.python.Method(
        __doc__ = "",
        args = {"attr"}
    )
    public org.python.Object __getattr__(org.python.Object name) {
        java.lang.String attr_name;
        try {
            attr_name = ((org.python.types.Str) name).value;
        } catch (java.lang.ClassCastException e) {
            throw new org.python.exceptions.TypeError("__getattr__(): attribute name must be string");
        }

        throw new org.python.exceptions.AttributeError(this, attr_name);
    }

    @org.python.Method(
        __doc__ = "",
        args = {"name"}
    )
    public org.python.Object __getattribute__(org.python.Object name) {
        java.lang.String attr_name;
        try {
            attr_name = ((org.python.types.Str) name).value;
        } catch (java.lang.ClassCastException e) {
            throw new org.python.exceptions.TypeError("__getattribute__(): attribute name must be string");
        }

        // Look for local instance attributes first
        // System.out.println("ATTRS " + this.attrs);
        org.python.Object value = this.attrs.get(attr_name);
        org.python.types.Type cls = (org.python.types.Type) this.attrs.get("__class__");
        if (value == null) {
            try {
                // No instance attribute; look for a class attribute.
                try {
                    value = cls.__getattribute__(name);
                } catch (org.python.exceptions.AttributeError e) {
                    // No class attribute; Try the __getattr__ helper.
                    value = this.__getattr__(name);
                }
            } catch (org.python.exceptions.AttributeError e) {
                // throw new org.python.exceptions.AttributeError(this, attr_name);
                throw e;
            }
        }

        return value.__get__(this, cls);
    }

    @org.python.Method(
        __doc__ = "",
        args = {"instance", "klass"}
    )
    public org.python.Object __get__(org.python.Object instance, org.python.Object klass) {
        return this;
    }

    @org.python.Method(
        __doc__ = "",
        args = {"name", "value"}
    )
    public void __setattr__(org.python.Object name, org.python.Object value) {
        java.lang.String attr_name;
        try {
            attr_name = ((org.python.types.Str) name).value;
        } catch (java.lang.ClassCastException e) {
            throw new org.python.exceptions.TypeError("__setattr__(): attribute name must be string");
        }

        // The base object can't have attribute set on it unless the attribute already exists.
        // System.out.println("SETATTR " + name + " = " + value);
        org.python.Object field = this.attrs.get(attr_name);
        org.python.types.Type cls = (org.python.types.Type) this.attrs.get("__class__");
        if (field == null) {
            try {
                field = cls.__getattribute__(name);
            } catch (org.python.exceptions.AttributeError e) {
            }
        }

        if (this.getClass() == org.python.types.Object.class) {
            if (field == null) {
                throw new org.python.exceptions.AttributeError(this, attr_name);
            }
        }

        try {
            field.__set__(this, cls, value);
        } catch (org.python.exceptions.AttributeError ae) {
            // System.out.println("Not a native field");
            this.attrs.put(attr_name, value);
        } catch (java.lang.NullPointerException npe) {
            // System.out.println("Not a native field");
            this.attrs.put(attr_name, value);
        }
    }

    /**
     * Part of the interface for org.python.Object, but not a public method.
     */
    public void __set__(org.python.Object instance, org.python.Object klass, org.python.Object value) {
        throw new org.python.exceptions.AttributeError(this, "");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"attr"}
    )
    public void __delattr__(org.python.Object name) {
        java.lang.String attr_name;
        try {
            attr_name = ((org.python.types.Str) name).value;
        } catch (java.lang.ClassCastException e) {
            throw new org.python.exceptions.TypeError("__delattr__(): attribute name must be string");
        }

        org.python.Object result = attrs.remove(attr_name);
        if (result == null) {
            throw new org.python.exceptions.AttributeError(this, attr_name);
        }
    }

    @org.python.Method(
        __doc__ = ""
    )
    public org.python.Object __dir__() {
        org.python.types.List names = new org.python.types.List(new java.util.ArrayList(this.attrs.keySet()));

        names.extend(this.attrs.get("__class__").__dir__());
        names.sort();

        return names;
    }

    /**
     * Section 3.3.4 - Customizing instance and subclass checks
     */
    // public org.python.Object __instancecheck__(org.python.Object instance) {
    //     throw new org.python.exceptions.AttributeError(this, "__instancecheck__");
    // }

    // public org.python.Object __subclasscheck__(org.python.Object subclass) {
    //     throw new org.python.exceptions.AttributeError(this, "__subclasscheck__");
    // }


    /**
     * Section 3.3.5 - Emulating callable objects
     */
    // public org.python.Object __call__(org.python.Object... args) {
    //     throw new org.python.exceptions.AttributeError(this, "__call__");
    // }


    /**
     * Section 3.3.6 - Emulating container types
     */

    @org.python.Method(
        __doc__ = ""
    )
    public org.python.Object __len__() {
        throw new org.python.exceptions.AttributeError(this, "__len__");
    }

    // public org.python.Object __length_hint__(java.util.List<org.python.Object> args, java.util.Map<java.lang.String, org.python.Object> kwargs, java.util.List<org.python.Object> default_args, java.util.Map<java.lang.String, org.python.Object> default_kwargs) {
    //     throw new org.python.exceptions.AttributeError(this, "__length_hint__");
    // }


    @org.python.Method(
        __doc__ = "",
        args = {"index"}
    )
    public org.python.Object __getitem__(org.python.Object index) {
        throw new org.python.exceptions.AttributeError(this, "__getitem__");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"key"}
    )
    public org.python.Object __missing__(org.python.Object key) {
        throw new org.python.exceptions.AttributeError(this, "__missing__");
    }


    @org.python.Method(
        __doc__ = "",
        args = {"index", "value"}
    )
    public void __setitem__(org.python.Object index, org.python.Object value) {
        throw new org.python.exceptions.AttributeError(this, "__setitem__");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"index"}
    )
    public void __delitem__(org.python.Object index) {
        throw new org.python.exceptions.AttributeError(this, "__delitem__");
    }

    @org.python.Method(
        __doc__ = ""
    )
    public org.python.Iterable __iter__() {
        throw new org.python.exceptions.AttributeError(this, "__iter__");
    }

    @org.python.Method(
        __doc__ = ""
    )
    public org.python.Iterable __reversed__() {
        throw new org.python.exceptions.AttributeError(this, "__reversed__");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"item"}
    )
    public org.python.Object __contains__(org.python.Object item) {
        throw new org.python.exceptions.AttributeError(this, "__contains__");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"item"}
    )
    public org.python.Object __not_contains__(org.python.Object item) {
        throw new org.python.exceptions.AttributeError(this, "__not_contains__");
    }

    /**
     * Section 3.3.7 - Emulating numeric types
     */

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __add__(org.python.Object other) {
        throw new org.python.exceptions.TypeError("unsupported operand type(s) for +: '" + this.typeName() + "' and '" + other.typeName() + "'");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __sub__(org.python.Object other) {
        throw new org.python.exceptions.TypeError("unsupported operand type(s) for -: '" + this.typeName() + "' and '" + other.typeName() + "'");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __mul__(org.python.Object other) {
        throw new org.python.exceptions.TypeError("unsupported operand type(s) for *: '" + this.typeName() + "' and '" + other.typeName() + "'");
    }


    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __truediv__(org.python.Object other) {
        throw new org.python.exceptions.TypeError("unsupported operand type(s) for /: '" + this.typeName() + "' and '" + other.typeName() + "'");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __floordiv__(org.python.Object other) {
        throw new org.python.exceptions.TypeError("unsupported operand type(s) for //: '" + this.typeName() + "' and '" + other.typeName() + "'");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __mod__(org.python.Object other) {
        throw new org.python.exceptions.TypeError("unsupported operand type(s) for %: '" + this.typeName() + "' and '" + other.typeName() + "'");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __divmod__(org.python.Object other) {
        throw new org.python.exceptions.TypeError("unsupported operand type(s) for divmod(): '" + this.typeName() + "' and '" + other.typeName() + "'");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other", "modulus"}
    )
    public org.python.Object __pow__(org.python.Object other, org.python.Object modulus) {
        throw new org.python.exceptions.TypeError("unsupported operand type(s) for ** or pow(): '" + this.typeName() + "' and '" + other.typeName() + "'");
    }


    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __lshift__(org.python.Object other) {
        throw new org.python.exceptions.TypeError("unsupported operand type(s) for <<: '" + this.typeName() + "' and '" + other.typeName() + "'");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __rshift__(org.python.Object other) {
        throw new org.python.exceptions.TypeError("unsupported operand type(s) for >>: '" + this.typeName() + "' and '" + other.typeName() + "'");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __and__(org.python.Object other) {
        throw new org.python.exceptions.TypeError("unsupported operand type(s) for &: '" + this.typeName() + "' and '" + other.typeName() + "'");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __xor__(org.python.Object other) {
        throw new org.python.exceptions.TypeError("unsupported operand type(s) for ^: '" + this.typeName() + "' and '" + other.typeName() + "'");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __or__(org.python.Object other) {
        throw new org.python.exceptions.TypeError("unsupported operand type(s) for |: '" + this.typeName() + "' and '" + other.typeName() + "'");
    }


    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __radd__(org.python.Object other) {
        throw new org.python.exceptions.AttributeError(this, "__radd__");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __rsub__(org.python.Object other) {
        throw new org.python.exceptions.AttributeError(this, "__rsub__");
    }
    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __rmul__(org.python.Object other) {
        throw new org.python.exceptions.AttributeError(this, "__rmul__");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __rtruediv__(org.python.Object other) {
        throw new org.python.exceptions.AttributeError(this, "__rtruediv__");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __rfloordiv__(org.python.Object other) {
        throw new org.python.exceptions.AttributeError(this, "__rfloordiv__");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __rmod__(org.python.Object other) {
        throw new org.python.exceptions.AttributeError(this, "__rmod__");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __rdivmod__(org.python.Object other) {
        throw new org.python.exceptions.AttributeError(this, "__rdivmod__");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __rpow__(org.python.Object other) {
        throw new org.python.exceptions.AttributeError(this, "__rpow__");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __rlshift__(org.python.Object other) {
        throw new org.python.exceptions.AttributeError(this, "__rlshift__");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __rrshift__(org.python.Object other) {
        throw new org.python.exceptions.AttributeError(this, "__rrshift__");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __rand__(org.python.Object other) {
        throw new org.python.exceptions.AttributeError(this, "__rand__");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __rxor__(org.python.Object other) {
        throw new org.python.exceptions.AttributeError(this, "__rxor__");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public org.python.Object __ror__(org.python.Object other) {
        throw new org.python.exceptions.AttributeError(this, "__ror__");
    }


    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public void __iadd__(org.python.Object other) {
        try {
            this.setValue(this.__add__(other));
        } catch (org.python.exceptions.TypeError e) {
            throw new org.python.exceptions.TypeError("unsupported operand type(s) for +=: '" + this.typeName() + "' and '" + other.typeName() + "'");
        }
    }


    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public void __isub__(org.python.Object other) {
        try {
            this.setValue(this.__sub__(other));
        } catch (org.python.exceptions.TypeError e) {
            throw new org.python.exceptions.TypeError("unsupported operand type(s) for -=: '" + this.typeName() + "' and '" + other.typeName() + "'");
        }
    }


    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public void __imul__(org.python.Object other) {
        try {
            this.setValue(this.__mul__(other));
        } catch (org.python.exceptions.TypeError e) {
            throw new org.python.exceptions.TypeError("unsupported operand type(s) for *=: '" + this.typeName() + "' and '" + other.typeName() + "'");
        }
    }


    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public void __itruediv__(org.python.Object other) {
        try {
            this.setValue(this.__truediv__(other));
        } catch (org.python.exceptions.TypeError e) {
            throw new org.python.exceptions.TypeError("unsupported operand type(s) for /=: '" + this.typeName() + "' and '" + other.typeName() + "'");
        }
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public void __ifloordiv__(org.python.Object other) {
        try {
            this.setValue(this.__floordiv__(other));
        } catch (org.python.exceptions.TypeError e) {
            throw new org.python.exceptions.TypeError("unsupported operand type(s) for //=: '" + this.typeName() + "' and '" + other.typeName() + "'");
        }
    }


    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public void __imod__(org.python.Object other) {
        try {
            this.setValue(this.__mod__(other));
        } catch (org.python.exceptions.TypeError e) {
            throw new org.python.exceptions.TypeError("unsupported operand type(s) for %=: '" + this.typeName() + "' and '" + other.typeName() + "'");
        }
    }


    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public void __ipow__(org.python.Object other) {
        try {
            this.setValue(this.__pow__(other, null));
        } catch (org.python.exceptions.TypeError e) {
            throw new org.python.exceptions.TypeError("unsupported operand type(s) for **=: '" + this.typeName() + "' and '" + other.typeName() + "'");
        }
    }


    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public void __ilshift__(org.python.Object other) {
        try {
            this.setValue(this.__lshift__(other));
        } catch (org.python.exceptions.TypeError e) {
            throw new org.python.exceptions.TypeError("unsupported operand type(s) for <<=: '" + this.typeName() + "' and '" + other.typeName() + "'");
        }
    }


    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public void __irshift__(org.python.Object other) {
        try {
            this.setValue(this.__rshift__(other));
        } catch (org.python.exceptions.TypeError e) {
            throw new org.python.exceptions.TypeError("unsupported operand type(s) for >>=: '" + this.typeName() + "' and '" + other.typeName() + "'");
        }
    }


    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public void __iand__(org.python.Object other) {
        try {
            this.setValue(this.__and__(other));
        } catch (org.python.exceptions.TypeError e) {
            throw new org.python.exceptions.TypeError("unsupported operand type(s) for &=: '" + this.typeName() + "' and '" + other.typeName() + "'");
        }
    }


    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public void __ixor__(org.python.Object other) {
        try {
            this.setValue(this.__xor__(other));
        } catch (org.python.exceptions.TypeError e) {
            throw new org.python.exceptions.TypeError("unsupported operand type(s) for ^=: '" + this.typeName() + "' and '" + other.typeName() + "'");
        }
    }

    @org.python.Method(
        __doc__ = "",
        args = {"other"}
    )
    public void __ior__(org.python.Object other) {
        try {
            this.setValue(this.__or__(other));
        } catch (org.python.exceptions.TypeError e) {
            throw new org.python.exceptions.TypeError("unsupported operand type(s) for |=: '" + this.typeName() + "' and '" + other.typeName() + "'");
        }
    }


    @org.python.Method(
        __doc__ = ""
    )
    public org.python.Object __neg__() {
        throw new org.python.exceptions.AttributeError(this, "__neg__");
    }

    @org.python.Method(
        __doc__ = ""
    )
    public org.python.Object __pos__() {
        throw new org.python.exceptions.AttributeError(this, "__pos__");
    }

    @org.python.Method(
        __doc__ = ""
    )
    public org.python.Object __abs__() {
        throw new org.python.exceptions.AttributeError(this, "__abs__");
    }

    @org.python.Method(
        __doc__ = ""
    )
    public org.python.Object __invert__() {
        throw new org.python.exceptions.AttributeError(this, "__invert__");
    }

    @org.python.Method(
        __doc__ = ""
    )
    public org.python.Object __not__() {
        return new org.python.types.Bool(!((org.python.types.Bool) this.__bool__()).value);
    }

    @org.python.Method(
        __doc__ = "",
        args = {"real", "imag"}
    )
    public org.python.Object __complex__(org.python.Object real, org.python.Object imag) {
        throw new org.python.exceptions.AttributeError(this, "__complex__");
    }

    @org.python.Method(
        __doc__ = ""
    )
    public org.python.Object __int__() {
        throw new org.python.exceptions.AttributeError(this, "__int__");
    }

    @org.python.Method(
        __doc__ = ""
    )
    public org.python.Object __float__() {
        throw new org.python.exceptions.AttributeError(this, "__float__");
    }

    @org.python.Method(
        __doc__ = "",
        args = {"ndigits"}
    )
    public org.python.Object __round__(org.python.Object ndigits) {
        throw new org.python.exceptions.AttributeError(this, "__round__");
    }

    /**
     * Section 3.3.8 - With statement context
     */
    @org.python.Method(
        __doc__ = ""
    )
    public org.python.Object __enter__() {
        throw new org.python.exceptions.AttributeError(this, "__enter__");
    }

    @org.python.Method(
        __doc__ = ""
    )
    public org.python.Object __exit__(org.python.Object exc_type, org.python.Object exc_value, org.python.Object traceback) {
        throw new org.python.exceptions.AttributeError(this, "__exit__");
    }
}