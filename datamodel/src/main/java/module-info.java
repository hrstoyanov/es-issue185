module issue185.datamodel {
    requires transitive issue185.utilities;
    requires transitive org.eclipse.store.storage.embedded;


    exports issue185.datamodel;
    exports issue185.datamodel._EntityBase;

    exports issue185.datamodel.business;
    exports issue185.datamodel.business._Business;
    exports issue185.datamodel.business._Hotel;


    exports issue185.datamodel.user;
    exports issue185.datamodel.user._User;
    exports issue185.datamodel.user._Team;
    exports issue185.datamodel.user._RoleAssignment;
}
