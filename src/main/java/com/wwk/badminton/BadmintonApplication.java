package com.wwk.badminton;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;


@Slf4j
@SpringBootApplication
public class BadmintonApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(BadmintonApplication.class, args);
        System.out.println(
                        "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu\n" +
                        "uuuuuuuuuuuuuuuuuuuu8888888888888888888888888888888888888888888888888888888888888888888888\n" +
                        "uuuuuuuuuuuuuuuuuuuu8888888888888888888888888888888888888888888888888888888888888888888888\n" +
                        "uuuuuuuuuuuuuuuuuuuu8888888!-.....-;8--......88%..!88.-6~...-^%a..%8a.13...-.8-.!%8..68886\n" +
                        "uuuuuuuuuuuuuuuuuuuu888888888i....18886....-%8888..8^.ai-+%8~.aa..-8a.u3.+88888.-6-.888888\n" +
                        "uuuuuuuuuuuuuuuuuuuu88888%6388u..6683888...8888888.. v8o.o881-+z.1-+u.1!.....%88...8888888\n" +
                        "uuuuuuuuuuuuuuuuuuuuiii!--..-.i.1u.....zuziiiiiiii!..ii~.;iiz.+v.ao.;-au--iiiiii...uiiiiii\n" +
                        "uuuuuuuuuuuuuuuuuuuuiiii....--ii!+....-.iiiiiiiiiii..ii1.-i!-.av.zi*..a1.-i!iii..i-.1iiiii\n" +
                        "uuuuuuuuuuuuuuuuuuuuiiiii--.-!iii!v.--;iiiiiiiiiiii-.!ii!.-.-iiv.v!%v.a1-..-.z-.iii--iviii\n" +
                        "uuuuuuuuuuuuuuuuuuuuiiiiiiiiiiiiii!-!i!!iiiiiiiiiiiiiiiiiiii!!%-@## 8iiiiiiiiiiiiiiiiiiiii\n" +
                        "uuuuuuuuuuuuuuuuuuuu!!i!33!!!**iii!6+8!i!!!!iiiiii!!ii!!!!ii!$.%*@-@#!i!i!!iiiii!!!iiiii!i\n" +
                        "i!iiiiii!3666ii8ziii1+3!3+iiii8-!in-+-z$@@@@@@@$!iiiiiiiiiiiii81@@niz%niiiiiiiiiiiiiiiiiii\n" +
                        "8.i38iii!8o-+3!8-!!!!6+6!+%8+!38#&+--&$$$$$$$$$$$$$$&@@@@%88888$%@#@-&%$%%%%%%%%%%%%%%%8%%\n" +
                        "3+u6vvzv6vvvz-zv-v3+vvavvv!6#$#@@@@@$$$$$$$$#@@@@@@@@@@@@@@@@zvvvvz+#@@*vz1^navon3$z~~~~~~\n" +
                        "3-ivv-uvvv!++3vvvvavvvvna&$#@@@@@@@@@@@---------#@@@@@@@@@@@@@&vi#%o6#$o*vn~*~nvvu^+^&a~~~\n" +
                        "v3+++1zvvvvvvvvvvvvvvv@$$$@@@@@@@@@@@@@@@------@@@@@@@8*$#@@@@@@vvvi@*u*.@*$;~ooov^***;3o~\n" +
                        "v~^^^^^^^;;;; ^^^^^^@$$$&@@@@@@%---@@@@@@.-----.@@@8-.;$@u-@@@@@^^^;@++~#v&o.*+--a---...6-\n" +
                        "v~^^^^^;;;;.^^^^^^~$$$$@-@@@@-;@*--+#o-.-----------#--------^---+^^*v+-+-un**+-++z---...-z\n" +
                        "vo^^^*;;;*-^^^^^^#$$$$$.----^----------i----------.-.1@@@*.---#-%^^+++^^ **-+++^+z.....--!\n" +
                        "vo^^;;;; ^^^^^^*#$$$$$@-.-8--------.--.--*--------@@@@.@@@@@&--&--vu*+.;*+---++^^u...-.-.;\n" +
                        "vn;;;;-^^^^^^^^@$$$$$$---3-----.~@@@@@@@@@&-----.@@@@@..  -3@@----;a-.+#++-+-++++a-----..*\n" +
                        "vn;;;-^^^^^^^^#$$$$$$@.-*+----+@@@@.;#@@@@@@^---3@@@       #@@+---;;-.-.-u+++++- o.-----.i\n" +
                        "vn; ^^^^^^^^+^$$$$$$$*--------@@@&        @@@----@@@@@@#  #@@@+++++----..--8^^.**o...--.-u\n" +
                        "vn+^^^^^^^^^^a$$$$$$$---------#@@@@@   n@@@@@+++++&@@@@@@;#@u*^^^^+~*+--....#~;;*n.-..-.8-\n" +
                        "vn^^ ^^^^^^o@@$$$$$$$---------+@@@@@@@ @@@@@^^^^^^^^+^;v~*^^**^^*^*+n*+---..-;;;;n-----v--\n" +
                        "vn^+^^^^+v-----.&%$$@---------++^3@@@@@@@n******************n***v^^^^^^+--..-!**-;...*z---\n" +
                        "vn^^^^^^n-------+-#$6-------+++^^^^****************^^^^^^^^^^^^^^^^+++-+i*.-.3++^*-n~-----\n" +
                        "vn^^^^^^+----------&--------++*^^**************^^^^^^^^^^^^^+++++++---------*uno^;--++++++\n" +
                        "nn^^^^^~--------------------++^^^^^^*^^^^^^^^^^^^++++++++--+----------------+i^^^;zzzzzzzz\n" +
                        "vv^^^^^@---------------------++++++++++++++++-------------------------------$n^^^^zzzzzzzz\n" +
                        "nn^^^^^%------------------------------------------------------------------.&v^^^^^o~~~;~oo\n" +
                        "vn^^^^^1--------------------------------------------------#ooi---------.-n^^+^^^^^zzzzzzzz\n" +
                        "vv^^^^^8-------------&+--.---------------------------------$oon+------+&+^^^z+^^^+zzzzzzzz\n" +
                        "vn^^^^^$.-------.*!;......  ..-1&@#%z+---.-+------------.-..-+n-z#@@3++^^^^*~^^^^^azzzzzzz\n" +
                        "vv^^^^^^^@i;3@~***@ .......@+  $-@..+*****ua!.---------aa*******+   ...8~~~no~~o~~zzzzzzzz\n" +
                        "~~~~~~~~~~~~~~~~~1 ........--~8-.n ........ %zi#ann&$au!................ !oz~~~oo~zzzzzzzz\n" +
                        "11iiiiii!!!ii11ui ...-* ...+.----%. ..........  ....-.   .-.   -........  13$#%^^^^^^^^^^^\n" +
                        "^^^^^^^^^^^^^^^$.... ***3366------8zzzzav^... .. .. -.$. ^;n&6z3*+ ....*+----.$^^^^^^^^^^^\n" +
                        "^^^^^^^^^^^^^^#.... ****#1.-------&azzzzzzzzzzzzzzzzzzzzzzzzz33!v*+ ...8-.---&^^^^^^^^^^^^\n" +
                        "^^^^^^^^^^^^^~.....******i#----+.--3~oo~~~ooo~~~oo~~~~o~~~o~o1i!!^**^**#---.@*^^^^^^^^^^^^\n" +
                        "^^^^^^^^^^^^^ .#!*-~#!#.----.1#@*-.......    ... ....   .^^+ ^*^*%*-o#+^---*-#^^^^^^^^^^^^\n" +
                        "^^^^^^^^^^^^8^----&--------3** ...........................^ .**^*&--^--^-^6u^o^^^^^^^^^^^^\n" +
                        "^^^^^^^^^^^^^^*---------6;*****........................^^.^+^^*^^o--+----636^^^^^^^^^^^^^^\n" +
                        "^^^^^^^^^^^^^^^&-.-.--$********....................... ^.^******;o*.----+$^^^^^^^^^^^^^^^^");
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        if (StringUtils.isEmpty(path)) {
            path = "";
        }
        log.info("\n----------------------------------------------------------\n\t" +
                "Application  is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "\n\t" +
                "network: \thttp://" + ip + ":" + port + path + "\n\t" +
                "----------------------------------------------------------");
    }

}
