package com.mycompany.preguntame;

/**
 *
 * @author Hackzll
 */

class BinaryNodeTree {
    private String question;
    private BinaryNodeTree yesNode;
    private BinaryNodeTree noNode;
    private String answer;

    public BinaryNodeTree(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public BinaryNodeTree getYesNode() {
        return yesNode;
    }

    public void setYesNode(BinaryNodeTree yesNode) {
        this.yesNode = yesNode;
    }

    public BinaryNodeTree getNoNode() {
        return noNode;
    }

    public void setNoNode(BinaryNodeTree noNode) {
        this.noNode = noNode;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isLeaf() {
        return (yesNode == null && noNode == null);
    }
}
