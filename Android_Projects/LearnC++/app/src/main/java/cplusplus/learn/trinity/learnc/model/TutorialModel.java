package cplusplus.learn.trinity.learnc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL-PC on 6/24/2017.
 */

public class TutorialModel implements Serializable {
    private String id;
    private String topic ;
    private Boolean isList,testAvailable;
    private List<TutorialModel> options;
    private int resource;

    public TutorialModel(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Boolean getList() {
        return isList;
    }

    public void setList(Boolean list) {
        isList = list;
    }

    public List<TutorialModel> getOptions() {
        return options;
    }

    public void setOptions(List<TutorialModel> options) {
        this.options = options;
    }

    public int getResource() {
        return resource;
    }

    public TutorialModel(String id, String topic, Boolean isList, List<TutorialModel> options, int resource,Boolean testAvailable) {
        this.id = id;
        this.topic = topic;
        this.isList = isList;
        this.options = options;
        this.resource = resource;
        this.testAvailable=testAvailable;
    }

    public Boolean getTestAvailable() {
        return testAvailable;
    }

    public void setTestAvailable(Boolean testAvailable) {
        this.testAvailable = testAvailable;
    }

    public void setResource(int resource) {
        this.resource = resource;

    }
}



