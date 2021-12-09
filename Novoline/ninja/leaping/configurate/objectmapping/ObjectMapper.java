package ninja.leaping.configurate.objectmapping;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import net.X9;
import net.aBM;
import net.axb;
import ninja.leaping.configurate.objectmapping.DefaultObjectMapperFactory;
import ninja.leaping.configurate.objectmapping.Setting;
import org.jetbrains.annotations.NotNull;

public class ObjectMapper {
   protected final Class clazz;
   private final Constructor constructor;
   private final Map cachedFields = new LinkedHashMap();

   public static ObjectMapper forClass(@NotNull Class var0) throws X9 {
      return DefaultObjectMapperFactory.getInstance().getMapper(var0);
   }

   public static axb b(@NotNull Object var0) throws X9 {
      return forClass(Objects.requireNonNull(var0).getClass()).a(var0);
   }

   protected ObjectMapper(Class var1) throws X9 {
      X9.b();
      this.clazz = var1;
      Constructor var3 = null;

      try {
         var3 = var1.getDeclaredConstructor(new Class[0]);
         var3.setAccessible(true);
      } catch (NoSuchMethodException var5) {
         ;
      }

      this.constructor = var3;
      Class var4 = var1;

      while(true) {
         this.collectFields(this.cachedFields, var4);
         if((var4 = var4.getSuperclass()).equals(Object.class)) {
            break;
         }
      }

   }

   public Class getClazz() {
      return this.clazz;
   }

   protected void collectFields(Map var1, Class var2) throws X9 {
      X9.b();
      Field[] var4 = var2.getDeclaredFields();
      int var5 = var4.length;
      int var6 = 0;
      if(var6 < var5) {
         Field var7 = var4[var6];
         if(var7.isAnnotationPresent(Setting.class)) {
            Setting var8 = (Setting)var7.getAnnotation(Setting.class);
            String var9 = var8.value();
            if(var9.isEmpty()) {
               throw new X9("Empty path");
            }

            aBM var10 = new aBM(var7, var8.comment());
            var7.setAccessible(true);
            if(!var1.containsKey(var9)) {
               var1.put(var9, var10);
            }
         }

         ++var6;
      }

   }

   protected Object constructObject() throws X9 {
      int[] var1 = X9.b();
      if(this.constructor == null) {
         throw new X9("No zero-arg constructor is available for class " + this.clazz + " but is required to construct new instances!");
      } else {
         try {
            return this.constructor.newInstance(new Object[0]);
         } catch (IllegalAccessException | InvocationTargetException | InstantiationException var3) {
            throw new X9("Unable to create instance of target class " + this.clazz, var3);
         }
      }
   }

   public boolean canCreateInstances() {
      return this.constructor != null;
   }

   public axb a(Object var1) {
      return new axb(this, var1);
   }

   public axb b() throws X9 {
      return new axb(this, this.constructObject());
   }

   public Class getMappedType() {
      return this.clazz;
   }

   static Map access$000(ObjectMapper var0) {
      return var0.cachedFields;
   }

   private static Exception a(Exception var0) {
      return var0;
   }
}
