package Core.AllureReports;

import TAF.Utils.LogManager.LogManager;
import TAF.Utils.OperatingSyatem.OSUtil;
import TAF.Utils.Terminal.Terminalutils;
import org.jsoup.Jsoup;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class AllureBinaryManager {

    //عملت برايفت كلاس بجيب اخر نسخه من allure واحفظو عندي اتوماتيك
    private static class lazyHolder {
        static final String VERSION = resolveVersion();// هنا بنخزن اخلر نسخه من  allure من جيت هب

        private static String resolveVersion() {
            try {
                String url = Jsoup.connect("https://github.com/allure-framework/allure2/releases/latest")
                        .followRedirects(true).execute().url().toString();
                return url.split("/tag/")[1];
            } catch (Exception e) {
                LogManager.Error("Unable to resolve allure version" + e.getMessage());
            }

            return "";
        }
    }

    //download the file
    private static Path Downloadzip(String version) {
        try {
            String url = AllureConstant.ALLURE_ZIP_BASE_URL + version + "/allure-commandline-" + version + ".zip";
            Path ZIPFILE = Paths.get(AllureConstant.EXTRACTION_DIR.toString(), "allure-" + version + ".zip");
            if (!Files.exists(ZIPFILE)) {
                Files.createDirectories(AllureConstant.EXTRACTION_DIR);
                try {
                    BufferedInputStream in = new BufferedInputStream(new URI(url).toURL().openStream());
                    OutputStream out = Files.newOutputStream(ZIPFILE);
                    {
                        in.transferTo(out);
                    }


                } catch (Exception e) {
                    LogManager.Error("Invalid url for allure download", e.getMessage());
                }

            }
            return ZIPFILE;
        } catch (Exception e) {
            LogManager.Error("Error downloading allure file", e.getMessage());
            return Paths.get("");
        }
    }


    //Extract the zip file
    private static void Extractzip(Path zippath) {
        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zippath))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                Path filepath = Paths.get(AllureConstant.EXTRACTION_DIR.toString(), File.separator, entry.getName());
                //Path filepath = AllureConstant.EXTRACTION_DIR.resolve(entry.getName());
                if (entry.isDirectory()) {
                    Files.createDirectories(filepath);
                } else {
                    Files.createDirectories(filepath.getParent());
                    Files.copy(zipInputStream, filepath, StandardCopyOption.REPLACE_EXISTING);
                }
            }

        } catch (Exception e) {
            LogManager.Error("Error extracting allure zip file", e.getMessage());
        }
    }


    public static void DownloadAndExtract() {
        try {
            String version = lazyHolder.VERSION;
            Path extractionDir = Paths.get(AllureConstant.EXTRACTION_DIR.toString(), "allure-" + version);
            //if allure exists before , do not download again
            if (Files.exists(extractionDir)) {
                LogManager.Info("allure binaries already exists.");
                return;
            }
            if (!OSUtil.getcurrentos().equals(OSUtil.OS.WINDOWS)) {
                Terminalutils.ExcuteTerminalaCommand("chmod", "u+x", AllureConstant.USER_DIR.toString());
            }

            Path zippath = Downloadzip(version);
            Extractzip(zippath);

            LogManager.Info("Allure binaries downloaded successfully");
            if (!OSUtil.getcurrentos().equals(OSUtil.OS.WINDOWS)) {
                Terminalutils.ExcuteTerminalaCommand("chmod", "u+x", getExcutable().toString());
            }
            Files.deleteIfExists(Files.list(AllureConstant.EXTRACTION_DIR)
                    .filter(p -> p.toString().endsWith(".zip")).findFirst().orElse(zippath));
            //Files.deleteIfExists(zippath);

        } catch (Exception e) {
            LogManager.Error("Error Downloading or Extracting binaries.", e.getMessage());
        }


    }

    public static Path getExcutable() {
        String version = lazyHolder.VERSION;
        Path binarypath = Paths.get(AllureConstant.EXTRACTION_DIR.toString(), "allure-" + version, "bin", "allure");
        return OSUtil.getcurrentos() == OSUtil.OS.WINDOWS ?
                binarypath.resolveSibling(binarypath.getFileName() + ".bat") : binarypath;
    }
}


