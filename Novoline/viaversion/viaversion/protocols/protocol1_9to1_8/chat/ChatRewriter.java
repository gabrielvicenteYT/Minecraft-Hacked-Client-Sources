package viaversion.viaversion.protocols.protocol1_9to1_8.chat;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.cq;
import viaversion.viaversion.api.data.UserConnection;
import viaversion.viaversion.protocols.protocol1_9to1_8.chat.GameMode;

public class ChatRewriter {
   public static void toClient(JsonObject var0, UserConnection var1) {
      int[] var2 = GameMode.b();
      if(var0.get("translate") != null && var0.get("translate").getAsString().equals("gameMode.changed")) {
         String var3 = ((cq)var1.b(cq.class)).h().getText();
         JsonObject var4 = new JsonObject();
         var4.addProperty("text", var3);
         var4.addProperty("color", "gray");
         var4.addProperty("italic", Boolean.valueOf(true));
         JsonArray var5 = new JsonArray();
         var5.add(var4);
         var0.add("with", var5);
      }

   }
}
