package uk.co.aperistudios.firma;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSavedData;

public class TimeData extends WorldSavedData {
	public TimeData(String name) {
		super(name);
	}
	private int day;
	private int month;
	private int year;
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void addDay(){
		addDay(1);
	}
	public void addDay(int days) {
		day+=days;
		while(day > Util.daysInMonth){
			day -= Util.daysInMonth;
			month++;
		}
		while(month > Util.monthsInYear){
			month -= Util.monthsInYear;
			year++;
		}
	}
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		year = nbt.getInteger("year");
		month = nbt.getInteger("month");
		day = nbt.getInteger("day");
	}
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		nbt.setInteger("year", year);
		nbt.setInteger("month", month);
		nbt.setInteger("day", day);
		return nbt;
	}
	
	@Override
	public String toString() {
		return "(CE "+day+"/"+month+"/"+year+" )";
	}
}
