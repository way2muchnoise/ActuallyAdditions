package ellpeck.actuallyadditions.event;

import ellpeck.actuallyadditions.manual.InventoryEvents;
import ellpeck.actuallyadditions.util.Util;

public class InitEvents{

    public static void init(){
        Util.logInfo("Initializing Events...");

        Util.registerEvent(new SmeltEvent());
        Util.registerEvent(new CraftEvent());
        Util.registerEvent(new KilledEvent());
        Util.registerEvent(new PickupEvent());
        Util.registerEvent(new TooltipEvent());
        Util.registerEvent(new EntityLivingEvent());
        Util.registerEvent(new WorldDecorationEvent());
        Util.registerEvent(new BucketFillEvent());
        Util.registerEvent(new InventoryEvents.InitGuiEvent());
        Util.registerEvent(new InventoryEvents.ButtonPressedEvent());
    }

}
