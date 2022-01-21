import backend.application.Application;

public class Main {


    public static void main(String[] args) {
        //Bugs found and not fixed in Windows (Linux distros have been tested and run without crashes/bugs)
        // Windows JOptionPanes show on bottom of all the windows and can't be alttabbed (nice os)
        // Try to move the window before confirming actions


        // Den teliwsa ta test,den eixa allo xrono,thelw ligi epieikeia mias kai hmoun monos =)
        // Oti exception/crash vreite anoikste ena issue sto github h steilte mou ena email na to diorthwsw gia na kanw to
        // repo public archive afou to kanw refactor ligo akoma. Steile mou ena email otan mporw na ksanakanw commit xwris
        // na epireastei to deadline
        Application.getInstance().run();
    }
}
