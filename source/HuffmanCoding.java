import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class HuffmanCoding
{
    static class Node implements Comparable<Node> {
        int freq;
        public int compareTo(Node other) {
            if (this.freq > other.freq)
                return 1;
            else if (this.freq < other.freq)
                return -1;
            else
                return 0;
        }
    }
    static class ValueNode extends Node {
        byte value;
        public ValueNode(byte value) {
            this.value = value;
            this.freq = 1;
        }
    }
    static class BranchNode extends Node {
        public BranchNode(Node left, Node right) {
            this.left = left;
            this.right = right;
            this.freq = left.freq + right.freq;
        }
        Node left, right;
    }
    public static void main(String args[]) {
        long startTime = System.currentTimeMillis();
        String data = readFile("C:\\Users\\User\\Eclipse-Workspace-Export\\Desktop\\MAIN\\HTML\\tetris.html");
        ArrayList<Node> list = new ArrayList<Node>();
        int[] indices = new int[256];
        for (byte b : data.getBytes()) {
            int bt = b + 128;
            if (indices[bt]==0) {
                list.add(new ValueNode(b));
                indices[bt]=list.size();
            } else {
                list.get(indices[bt]-1).freq++;
            }
        }
        Collections.sort(list);
        while (list.size() > 1) {
            Node a = list.get(0);
            list.remove(0);
            Node b = list.get(0);
            list.remove(0);
            list.add(new BranchNode(a, b));
            Collections.sort(list);
        }
        //System.out.println(getNodeRep(list.get(0)));
        String[] treeIndices = new String[256];
        traverse("", list.get(0), treeIndices);
        /*for (String s : treeIndices) {
            if (s!=null)
            System.out.println(s);
        }*/
        String out = "";
        for (byte b : data.getBytes()) {
            out += treeIndices[b+128];
        }
        //System.out.println(out.length());
        
        System.out.println("Time taken: " + ((System.currentTimeMillis() - startTime)/1000d) + " seconds");
    }
    
    private static void traverse(String index, Node node, String[] data) {
        if (node instanceof ValueNode) {
            data[((ValueNode)node).value+128] = index;
        } else if (node instanceof BranchNode) {
            BranchNode bn = (BranchNode)node;
            traverse(index + "0", bn.left, data);
            traverse(index + "1", bn.right, data);
        }
    }
    
    private static String getNodeRep(Node node) {
        String out = "";
        out += "freq: " + node.freq + ",\n";
        if (node instanceof ValueNode) {
            out += "value: " + ((ValueNode)node).value;
        } else if (node instanceof BranchNode) {
            BranchNode bn = (BranchNode)node;
            out += "left: " + getNodeRep(bn.left) + ",\n";
            out += "right: " + getNodeRep(bn.right);
        }
        out = "{\n" + indent(out) + "\n}";
        return out;
    }
    
    private static String indent(String original) {
        String out = "    ";
        for (byte b : original.getBytes()) {
            out += (char)b;
            if ( (char)b == '\n') {
                out += "    ";
            }
        }
        return out;
    }
    
    private static String readFile(String fileName) {
        try {
            File file = new File(fileName);
            FileInputStream fis = new FileInputStream(file);
            
            System.out.println("File size: " + file.length()*8);
            
            byte[] buffer = new byte[2048];
            int res;
            String out = "";
            while ( (res=fis.read(buffer)) != -1) {
                for (int i = 0; i < res; i++)
                    out += (char)buffer[i];
            }
            fis.close();
            return out;
        } catch (IOException e) {
            throw new RuntimeException("Exception while reading file " + fileName, e);
        }
    }
}