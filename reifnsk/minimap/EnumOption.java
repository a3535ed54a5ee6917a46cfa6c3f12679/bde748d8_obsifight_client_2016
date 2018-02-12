package reifnsk.minimap;

public enum EnumOption {
   MINIMAP("ObsiFight MiniMap", 0, new EnumOptionValue[]{EnumOptionValue.ENABLE, EnumOptionValue.DISABLE}),
   ENTITIES_RADAR_OPTION("EntitiesRadar Options", 0, new EnumOptionValue[]{EnumOptionValue.SUB_OPTION}),
   MARKER_OPTION("Marker Options", 0, new EnumOptionValue[]{EnumOptionValue.SUB_OPTION}),
   TEXTURE("Texture", 1, new EnumOptionValue[]{EnumOptionValue.REI_MINIMAP, EnumOptionValue.ZAN_MINIMAP}),
   MARKER("Marker", 4, new EnumOptionValue[]{EnumOptionValue.ENABLE, EnumOptionValue.DISABLE}),
   MARKER_ICON("Icon", 4, new EnumOptionValue[]{EnumOptionValue.ENABLE, EnumOptionValue.DISABLE}),
   MARKER_LABEL("Label", 4, new EnumOptionValue[]{EnumOptionValue.ENABLE, EnumOptionValue.DISABLE}),
   MARKER_DISTANCE("Distance", 4, new EnumOptionValue[]{EnumOptionValue.ENABLE, EnumOptionValue.DISABLE}),
   ABOUT_VERSION("Version", 5, new EnumOptionValue[]{EnumOptionValue.VERSION}),
   ABOUT_AUTHER("Author", 5, new EnumOptionValue[]{EnumOptionValue.AUTHOR}),
   ENG_FORUM("Forum (en)", 5, new EnumOptionValue[]{EnumOptionValue.SUB_OPTION}),
   JP_FORUM("Forum (jp)", 5, new EnumOptionValue[]{EnumOptionValue.SUB_OPTION});
   public static final int maxPage;
   private String name;
   private EnumOptionValue[] values;
   private int page;

   private EnumOption(String name, int page, EnumOptionValue ... values) {
      this.name = name;
      this.page = page;
      this.values = values;
   }

   public String getText() {
      return this.name;
   }

   public int getPage() {
      return this.page;
   }

   public int getValueNum() {
      return this.values.length;
   }

   public EnumOptionValue getValue(int i) {
      return i >= 0 && i < this.values.length?this.values[i]:this.values[0];
   }

   public int getValue(EnumOptionValue v) {
      for(int i = 0; i < this.values.length; ++i) {
         if(this.values[i] == v) {
            return i;
         }
      }

      return -1;
   }

   static {
      int max = 0;
      EnumOption[] arr$ = values();
      int len$ = arr$.length;

      for(int i$ = 0; i$ < len$; ++i$) {
         EnumOption eo = arr$[i$];
         if(max < eo.page) {
            max = eo.page;
         }
      }

      maxPage = max;
   }
}
