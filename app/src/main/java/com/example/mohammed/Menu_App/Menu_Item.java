package com.example.mohammed.Menu_App;

    public class Menu_Item {

    private String mMenuitem;
    private String mMenuitemDescription;
    private static final int No_IMAGE_PROVIDED = -1;
    private int mIgameResourceId = No_IMAGE_PROVIDED ;
    private int mAudioResourceId ;


   public Menu_Item(String Menuitem, String MenuitemDescription, int imageResourceId, int audioResourceId){
       mMenuitem = Menuitem;
       mMenuitemDescription = MenuitemDescription;
       mIgameResourceId = imageResourceId;
       mAudioResourceId = audioResourceId;}


    public Menu_Item(int audioResourceId){mAudioResourceId = audioResourceId;}

    public String  getmMenuitem(){ return mMenuitem; }
    public String getmMenuitemDescription() {return mMenuitemDescription;}

    public int getmImageResourceId() {return mIgameResourceId ; }

    public int getmAudioResourceId(){return mAudioResourceId;}

    public boolean hasImage(){ return  mIgameResourceId != No_IMAGE_PROVIDED; }

    @Override
    public String toString() {
        return "Menu_Item{" +
                "mMenuitem='" + mMenuitem + '\'' +
                ", mMenuitemDescription=" + mMenuitemDescription +
                ", mIgameResourceId=" + mIgameResourceId +
                ", mAudioResourceId=" + mAudioResourceId +
                '}';
    }
}
