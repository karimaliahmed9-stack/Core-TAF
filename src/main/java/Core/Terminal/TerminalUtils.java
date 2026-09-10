//package TAF.Utils.Terminal;
//
//import TAF.Utils.LogManager.LogManager;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//للتعامل مع ال CMD علي اي نظام تشغيل

//public class Terminalutils {
// public static void excuteterminalaCommand(String... Commandparts) {
// try {
//   Process process = Runtime.getRuntime().exec(Commandparts);
//  int exitCode = process.waitFor();
//  if (exitCode != 0) {
//  LogManager.Info("Command failed with exits code" + exitCode);
//  }
//  } catch (Exception e) {
//  LogManager.Error("Failed to executed terminal command" + String.join("", Commandparts), e.getMessage());
//}

//    public static void ExcuteTerminalaCommand(String... commandParts) {
//        try {
//            Process process = Runtime.getRuntime().exec(commandParts); // allure Generate
//            int exitCode = process.waitFor();
//            if (exitCode != 0) {
//                LogManager.Info("Command failed with exit code " + exitCode);
//            }
//        } catch (IOException | InterruptedException e ) {
//            LogManager.Error("Failed to execute terminal command: " + String.join(" ", commandParts), e.getMessage());
//        }
//    }
package Core.Terminal;

import Core.LogManager.LogManager;

import java.io.IOException;

public class TerminalUtils {

    //...معناها ببعت اراي استرينج في التيرمنال
    public static void ExecuteTerminalCommand(String... Commandparts) {
        try {
            // allure -o reports --single-file --clean
            //دا الي عايز اكتبو داخل التيرمنال
            Process process = Runtime.getRuntime().exec(Commandparts);
            //استني لما يكتب ويخلص
            int exitCode = process.waitFor();
            //لو في حاجه غلط في path او في الكومند لين نفسو
            if (exitCode != 0) {
                LogManager.Info("Command failed with exits code" + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            LogManager.Error("Failed to executed terminal command" + String.join("", Commandparts), e.getMessage());
        }

    }
}