package com.dovi.alarm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsm2016 on 2018-06-18.
 */

public class DataIOManager {

    public List<RecyclerItem> readAlarmList(){
        try(BufferedReader br = new BufferedReader(new FileReader("AlarmLists.txt"))) {
            String line;

            int alarmListNum = Integer.parseInt(br.readLine());
            br.readLine();

            if(alarmListNum <= 0)
                return null;

            List<RecyclerItem> recyclerItemList = new ArrayList<RecyclerItem>();

            for(int i = 0; i < alarmListNum; i++) {
                int itemNum = Integer.parseInt(br.readLine());
                boolean isChecked = Boolean.parseBoolean(br.readLine());
                String textTime = br.readLine();
                br.readLine();

                String textHour = textTime.split(":")[0];
                String textMin = textTime.split(":")[1];

                RecyclerItem recyclerItem = new RecyclerItem(isChecked, textTime, "0000000");
                recyclerItemList.add(recyclerItem);
            }

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void writeAlarmList(List<RecyclerItem> recyclerItemList) {
        try (PrintWriter writer = new PrintWriter("AlarmLists.txt")) {
            if (recyclerItemList.size() == 0 || recyclerItemList == null)
                return;

            writer.println(recyclerItemList.size());
            writer.println();

            for (RecyclerItem recyclerItem : recyclerItemList) {
                writer.println(recyclerItem.getItemNum());
                writer.println(recyclerItem.getIsChecked());
                writer.println(recyclerItem.getTextTime());
                writer.println(recyclerItem.getTextDate());
                writer.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
