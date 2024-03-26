package issue185.utilities;

public interface HTMX {
     interface RequestHeaders{
        String HX_BOOSTED = "HX-Boosted";
        String HX_Current_URL="HX-Current-URL";
        String HX_History_Restore_Request = "HX-History-Restore-Request";
        String HX_Prompt="HX-Prompt";
        String HX_Request ="HX-Request";
        String HX_Target = "HX-Target";
        String HX_Trigger_Name = "HX-Trigger-Name";
        String HX_Trigger = "HX-Trigger";
    }
    interface ResponseHeaders{
        String HX_Location ="HX-Location";
        String HX_Push_Url = "HX-Push-Url";
        String HX_Redirect = "HX-Redirect";
        String HX_Refresh = "HX-Refresh";
        String HX_Replace_Url = "HX-Replace-Url";
        String HX_Reswap = "HX-Reswap";
        String HX_Retarget = "HX-Retarget";
        String HX_Reselect = "HX-Reselect";
        String HX_Trigger = "HX-Trigger";
        String HX_Trigger_After_Settle ="HX-Trigger-After-Settle";
        String HX_Trigger_After_Swap ="HX-Trigger-After-Swap";
    }

    static String asHeader(String input){
         return input.replaceAll("\\s+|\\n", "");
    }

    static String asHeaderEnhanced(String input){
        input = input.replaceAll("\n","");
        input = input.replaceAll("\\s+", " ");
        return input;
    }

}
