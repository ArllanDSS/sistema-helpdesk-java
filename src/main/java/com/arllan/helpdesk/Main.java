package com.arllan.helpdesk;

import com.arllan.helpdesk.view.HelpDeskConsole;

public class Main {
    public static void main(String[] args) {
        // A Main apenas liga o sistema
        HelpDeskConsole sistema = new HelpDeskConsole();
        sistema.exibirMenu();
    }
}