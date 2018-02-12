package optifine;

import java.util.HashMap;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;

public class CrashReporter {
   public static void onCrashReport(CrashReport crashReport, CrashReportCategory category) {
      try {
         GameSettings var10 = Config.getGameSettings();
         if(var10 == null) {
            return;
         }

         if(!var10.snooperEnabled) {
            return;
         }

         Throwable cause = crashReport.getCrashCause();
         if(cause == null) {
            return;
         }

         if(cause.getClass() == Throwable.class) {
            return;
         }

         if(cause.getClass().getName().contains(".fml.client.SplashProgress")) {
            return;
         }

         extendCrashReport(category);
         String url = "http://optifine.net/crashReport";
         String reportStr = makeReport(crashReport);
         byte[] content = reportStr.getBytes("ASCII");
         IFileUploadListener listener = new IFileUploadListener() {
            public void fileUploadFinished(String url, byte[] content, Throwable exception) {}
         };
         HashMap headers = new HashMap();
         headers.put("OF-Version", Config.getVersion());
         headers.put("OF-Summary", makeSummary(crashReport));
         FileUploadThread fut = new FileUploadThread(url, headers, content, listener);
         fut.setPriority(10);
         fut.start();
         Thread.sleep(1000L);
      } catch (Exception var101) {
         Config.dbg(var101.getClass().getName() + ": " + var101.getMessage());
      }
   }

   private static String makeReport(CrashReport crashReport) {
      StringBuffer sb = new StringBuffer();
      sb.append("OptiFineVersion: " + Config.getVersion() + "\n");
      sb.append("Summary: " + makeSummary(crashReport) + "\n");
      sb.append("\n");
      sb.append(crashReport.getCompleteReport());
      sb.append("\n");
      return sb.toString();
   }

   private static String makeSummary(CrashReport crashReport) {
      Throwable t = crashReport.getCrashCause();
      if(t == null) {
         return "Unknown";
      } else {
         StackTraceElement[] traces = t.getStackTrace();
         String firstTrace = "unknown";
         if(traces.length > 0) {
            firstTrace = traces[0].toString().trim();
         }

         String sum = t.getClass().getName() + ": " + t.getMessage() + " (" + crashReport.getDescription() + ") [" + firstTrace + "]";
         return sum;
      }
   }

   public static void extendCrashReport(CrashReportCategory cat) {
      cat.addCrashSection("OptiFine Version", Config.getVersion());
      if(Config.getGameSettings() != null) {
         cat.addCrashSection("Render Distance Chunks", "" + Config.getChunkViewDistance());
         cat.addCrashSection("Mipmaps", "" + Config.getMipmapLevels());
         cat.addCrashSection("Anisotropic Filtering", "" + Config.getAnisotropicFilterLevel());
         cat.addCrashSection("Antialiasing", "" + Config.getAntialiasingLevel());
         cat.addCrashSection("Multitexture", "" + Config.isMultiTexture());
      }

      cat.addCrashSection("OpenGlVersion", "" + Config.openGlVersion);
      cat.addCrashSection("OpenGlRenderer", "" + Config.openGlRenderer);
      cat.addCrashSection("OpenGlVendor", "" + Config.openGlVendor);
      cat.addCrashSection("CpuCount", "" + Config.getAvailableProcessors());
   }
}
