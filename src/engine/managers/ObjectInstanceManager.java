package engine.managers;

import engine.objects.BaseObject;
import engine.screens.Screen;

import java.util.ArrayList;
import java.util.HashMap;

public class ObjectInstanceManager {
    private HashMap<Class<? extends BaseObject>, ArrayList<BaseObject>> data = new HashMap<>();
    private static ObjectInstanceManager instance = null;
    public static ObjectInstanceManager getInstance() {
        if(instance == null) {
            instance = new ObjectInstanceManager();
        }
        return instance;
    }
    private ObjectInstanceManager() {}
    public void registerClass(Class<? extends BaseObject> cls) {
        data.put(cls,new ArrayList<>());
    }
    public void addInstance(BaseObject object) {
        Class<? extends BaseObject> cls = object.getClass();
        if(!data.containsKey(cls)) {registerClass(cls);}
        data.get(cls).add(object);
    }
    public void addInstance(BaseObject object, Class<? extends BaseObject> cls) {
        if(!data.containsKey(cls)) {registerClass(cls);}
        data.get(cls).add(object);
    }
    public void removeInstance(BaseObject object) {
        Class<? extends BaseObject> cls = object.getClass();
        if(!data.containsKey(cls)) {registerClass(cls);}
        data.get(cls).remove(object);
    }
    public void removeInstance(BaseObject object, Class<? extends BaseObject> cls) {
        if(!data.containsKey(cls)) {registerClass(cls);}
        data.get(cls).remove(object);
    }
    public ArrayList<BaseObject> getArrayList(Class<? extends BaseObject> cls) {
        return (ArrayList<BaseObject>) data.get(cls).clone();
    }
    public ArrayList<BaseObject> getArrayList(Class<? extends BaseObject> cls, Screen screen) {
        ArrayList<BaseObject> resultarray = new ArrayList<>();
        if(!data.containsKey(cls)) {registerClass(cls);}
        for(BaseObject object: data.get(cls)) {
            if(object.getScreen().equals(screen)) {
                resultarray.add(object);
            }
        }
        return resultarray;
    }
}
