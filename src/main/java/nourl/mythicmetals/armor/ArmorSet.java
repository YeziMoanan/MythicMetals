package nourl.mythicmetals.armor;

import io.wispforest.owo.itemgroup.OwoItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import nourl.mythicmetals.MythicMetals;
import nourl.mythicmetals.misc.RegistryHelper;

import java.util.List;
import java.util.function.Consumer;

public class ArmorSet {

    private final ArmorItem helmet;
    private final ArmorItem chestplate;
    private final ArmorItem leggings;
    private final ArmorItem boots;

    private final List<ArmorItem> armorSet;

    public ArmorItem baseArmorItem(ArmorMaterial material, ArmorItem.Type slot, Consumer<OwoItemSettings> settingsProcessor) {
        final var settings = new OwoItemSettings().group(MythicMetals.TABBED_GROUP).tab(3);
        settingsProcessor.accept(settings);
        return this.makeItem(material, slot, settings);
    }

    public ArmorSet(ArmorMaterial material) {
        this(material, settings -> {
        });
    }

    public ArmorSet(ArmorMaterial material, Consumer<OwoItemSettings> settingsProcessor) {
        this.helmet = baseArmorItem(material, ArmorItem.Type.HELMET, settingsProcessor);
        this.chestplate = baseArmorItem(material, ArmorItem.Type.CHESTPLATE, settingsProcessor);
        this.leggings = baseArmorItem(material, ArmorItem.Type.LEGGINGS, settingsProcessor);
        this.boots = baseArmorItem(material, ArmorItem.Type.BOOTS, settingsProcessor);
        this.armorSet = List.of(helmet, chestplate, leggings, boots);
    }

    public void register(String name) {
        Registry.register(Registries.ITEM, RegistryHelper.id(name + "_helmet"), helmet);
        Registry.register(Registries.ITEM, RegistryHelper.id(name + "_chestplate"), chestplate);
        Registry.register(Registries.ITEM, RegistryHelper.id(name + "_leggings"), leggings);
        Registry.register(Registries.ITEM, RegistryHelper.id(name + "_boots"), boots);
    }

    public void register(String modid, String name) {
        Registry.register(Registries.ITEM, new Identifier(modid, name + "_helmet"), helmet);
        Registry.register(Registries.ITEM, new Identifier(modid, name + "_chestplate"), chestplate);
        Registry.register(Registries.ITEM, new Identifier(modid, name + "_leggings"), leggings);
        Registry.register(Registries.ITEM, new Identifier(modid, name + "_boots"), boots);
    }

    protected ArmorItem makeItem(ArmorMaterial material, ArmorItem.Type slot, OwoItemSettings settings) {
        return new ArmorItem(material, slot, settings);
    }

    public ArmorItem getHelmet() {
        return helmet;
    }

    public ArmorItem getChestplate() {
        return chestplate;
    }

    public ArmorItem getLeggings() {
        return leggings;
    }

    public ArmorItem getBoots() {
        return boots;
    }

    public List<ArmorItem> getArmorItems() {
        return armorSet;
    }

    public boolean isInArmorSet(ItemStack stack) {
        return this.getArmorItems().contains(stack.getItem());
    }
}
