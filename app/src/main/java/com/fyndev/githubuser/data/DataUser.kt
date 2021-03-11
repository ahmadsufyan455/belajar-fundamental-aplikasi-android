package com.fyndev.githubuser.data

import android.net.Uri

object DataUser {

    fun getDataUser(): List<User> {

        val listUser = mutableListOf<User>()

        listUser.add(
            User(
                "JakeWharton",
                "Jake Wharton",
                "${Uri.parse("android.resource://com.fyndev.githubuser/drawable/user1")}",
                "56995",
                "12",
                "102",
                "Pittsburgh, PA, USA",
                "Google, Inc."
            )
        )
        listUser.add(
            User(
                "amitshekhariitbhu",
                "AMIT SHEKHAR",
                "${Uri.parse("android.resource://com.fyndev.githubuser/drawable/user2")}",
                "5153",
                "2",
                "37",
                "New Delhi, India",
                "@MindOrksOpenSource"
            )
        )
        listUser.add(
            User(
                "romainguy",
                "Romain Guy",
                "${Uri.parse("android.resource://com.fyndev.githubuser/drawable/user3")}",
                "7972",
                "0",
                "9",
                "California",
                "Google"
            )
        )
        listUser.add(
            User(
                "chrisbanes",
                "Chris Banes",
                "${Uri.parse("android.resource://com.fyndev.githubuser/drawable/user4")}",
                "14725",
                "1",
                "30",
                "Sydney, Australia",
                "@google working on @android"
            )
        )
        listUser.add(
            User(
                "tipsy",
                "David",
                "${Uri.parse("android.resource://com.fyndev.githubuser/drawable/user5")}",
                "788",
                "0",
                "56",
                "Trondheim, Norway",
                "@Working Group Two"
            )
        )
        listUser.add(
            User(
                "ravi8x",
                "Ravi Tamada",
                "${Uri.parse("android.resource://com.fyndev.githubuser/drawable/user6")}",
                "18628",
                "3",
                "28",
                "India",
                "AndroidHive | Droid5"
            )
        )
        listUser.add(
            User(
                "jasoet",
                "Deny Prasetyo",
                "${Uri.parse("android.resource://com.fyndev.githubuser/drawable/user7")}",
                "277",
                "39",
                "44",
                "Kotagede, Yogyakarta, Indonesia",
                "@gojek-engineering"
            )
        )
        listUser.add(
            User(
                "budioktaviyan",
                "Budi Oktaviyan",
                "${Uri.parse("android.resource://com.fyndev.githubuser/drawable/user8")}",
                "178",
                "23",
                "110",
                "Jakarta, Indonesia",
                "@KotlinID"
            )
        )
        listUser.add(
            User(
                "hendisantika",
                "Hendi Santika",
                "${Uri.parse("android.resource://com.fyndev.githubuser/drawable/user9")}",
                "428",
                "61",
                "1064",
                "Bojongsoang - Bandung Jawa Barat",
                "@JVMDeveloperID @KotlinID @IDDevOps"
            )
        )
        listUser.add(
            User(
                "sidiqpermana",
                "Sidiq Permana",
                "${Uri.parse("android.resource://com.fyndev.githubuser/drawable/user10")}",
                "465",
                "10",
                "65",
                "Jakarta Indonesia",
                "Nusantara Beta Studio"
            )
        )

        return listUser

    }

}