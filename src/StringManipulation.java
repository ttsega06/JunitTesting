public class StringManipulation implements StringManipulationInterface {

    private String string;

    public StringManipulation() {
        string = null;
    }

    @Override
    public String getString() {
        return string;
    }

    @Override
    public void setString(String string) {
        this.string = string;
    }

    @Override
    public int count() {
        if (string == null) {
            return 0;
        }
        int co = 0;
        boolean isWord = false;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(0) != ' ' && !isWord) {
                isWord = true;
            } else if (string.charAt(i) == ' ' && isWord) {
                co++;
                isWord = false;
            }
        }
        if (isWord) {
            co++;
        }
        return co;
    }

    @Override
    public String removeNthCharacter(int n, boolean maintainSpacing) {
        if (string == null) {
            return "";
        }
        if (n > string.length()) {
            throw new IndexOutOfBoundsException("remove nth char: n greater than length of string");
        }
        if (n <= 0) {
            throw new IllegalArgumentException("remove nth char: n is less than or equal to zero");
        }

        String output = string;
        int idx = n - 1;
        while (idx <= output.length() - 1) {
            int restOfWord = idx + 1;
            if (restOfWord > output.length()) {
                restOfWord = output.length();
            }
            if (maintainSpacing) {
                output = output.substring(0, idx) + " " + output.substring(restOfWord);
            } else {

                output = output.substring(0, idx) + output.substring(restOfWord);
                idx--;
            }
            idx += n;
        }
        return output;
    }

    @Override
    public String[] getSubStrings(int startWord, int endWord) {
        startWord--;
        endWord--;

        if (startWord <= 0 || endWord <= 0 || startWord > endWord) {
            throw new IllegalArgumentException("getSubString: start or end word invalid");
        }


        String output = string;
        for (int i = 0; i < output.length() - 1; i++) {
            if (output.charAt(i) == ' ' && output.charAt(i + 1) == ' ') {
                output = output.substring(0, i) + output.substring(i + 1);
            }
        }
        String words[] = output.split(" ");
        if (words.length < endWord) {
            throw new IndexOutOfBoundsException("getSubString: endWord larger than num words");
        }

        int numWords = endWord - startWord + 1;
        String ans[] = new String[numWords];
        int ansIdx = 0;
        for (int i = startWord; i <= endWord; i++) {
            ans[ansIdx] = words[i];
            ansIdx++;
        }
        return ans;
    }

    @Override
    public String restoreString(int[] indices) {

        char out[] = new char[string.length()];

        if (out.length != indices.length) {
            throw new IllegalArgumentException("restoreString: invalid number of indices");
        }
        for (int i = 0; i < indices.length; i++) {
            char letterToMove = string.charAt(i);
            int idxToMoveTo = indices[i];
            if (idxToMoveTo < 0 || idxToMoveTo > string.length()) {
                throw new IndexOutOfBoundsException("restoreString: index out of range");
            }
            out[idxToMoveTo] = letterToMove;
        }
        String output = "";
        for (int i = 0; i < out.length; i++) {
            output += out[i];
        }
        return output;
    }

}
