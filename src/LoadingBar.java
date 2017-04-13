/**
 * Created by Tomov on 13.4.2017 г..
 */
public class LoadingBar {
    private int maxProgress;
    private int currentProgressPercent;
    public LoadingBar(int maxProgress) {
        this.maxProgress = maxProgress;
    }
    public void start(){
        currentProgressPercent = 0;
        System.out.print("|");
    }
    public void update(int newProgress) {
        int percentOfProgress = (newProgress*100)/maxProgress;
        for(int i = 0; i < percentOfProgress - currentProgressPercent; i++) {
            System.out.print("▯");
        }
        currentProgressPercent = percentOfProgress;
        if(newProgress==maxProgress || newProgress==maxProgress-1) { // because ill be using it zero based
            System.out.println("|");
        }
    }

}
