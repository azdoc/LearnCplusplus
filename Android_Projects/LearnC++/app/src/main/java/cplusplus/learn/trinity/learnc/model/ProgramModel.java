package cplusplus.learn.trinity.learnc.model;

/**
 * Created by DELL-PC on 6/24/2017.
 */

public class ProgramModel {
    private String title;
    private int program;
    private int output;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProgram() {
        return program;
    }

    public void setProgram(int program) {
        this.program = program;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    public ProgramModel(String title, int program, int output) {
        this.title = title;
        this.program = program;
        this.output = output;
    }
}

