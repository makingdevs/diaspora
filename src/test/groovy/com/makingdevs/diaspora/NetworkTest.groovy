package com.makingdevs.diaspora

import org.junit.Test

class NetworkTest {

  def network = Network.instance

  @Test
  void commonFollorwersTest(){
    def user1 = [user1: ["user2", "user3", "user4"]]
    def user2 = [user2: ["user3", "user4", "user5"]]

    assert ["user3", "user4"] == network.commonFollowersFor(user1, user2)
  }

  @Test
  void followingUsersTest(){

    def user3 = [user3: ["user8", "user5", "user6"]]
    def user4 = [user4: ["user6", "user5", "user8"]]
    def user5 = [user5: ["user3", "user7", "user4"]]

    def grid = [user3, user4, user5]

    assert [
      user3: ["user5"],
      user4: ["user5"],
      user5: ["user3", "user4"]
    ] == network.gridOfUsersAndFollowers(grid)

  }

  @Test
  void graphvizTest(){
    def user1 = [user1: ["user2", "user3", "user4"]]
    def user2 = [user2: ["user3", "user4", "user5"]]

    def user3 = [user3: ["user8", "user5", "user6"]]
    def user4 = [user4: ["user6", "user5", "user8"]]
    def user5 = [user5: ["user3", "user7", "user4"]]

    def grid = [user3, user4, user5]

    def graphCommonFollowers = network.graphvizForFollowers(user1, user2)
    def graph = network.gridOfUsersAndFollowers(grid)
    assert "user1 -> user3;\nuser2 -> user3;\nuser1 -> user4;\nuser2 -> user4;".trim() == graphCommonFollowers.trim()
    assert "user3 -> user5;\nuser4 -> user5;\nuser5 -> user3;\nuser5 -> user4;".trim() ==  network.graphvizForFollowersOfFollowers(graph).trim()
  }
}
