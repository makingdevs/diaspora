package com.makingdevs.diaspora

import org.junit.Test

class NetworkTest {

  def network = Network.instance

  @Test
  void commonFollorwersTest(){
    def user1Followers = [user1: ["user2", "user3", "user4"]]
    def user2Followers = [user2: ["user3", "user4", "user5"]]

    assert ["user3", "user4"] == network.commonFollowers(user1Followers, user2Followers)
  }

  @Test
  void followingUsersTest(){

    def user3Followers = [user3: ["user8", "user5", "user6"]]
    def user4Followers = [user4: ["user6", "user5", "user8"]]
    def user5Followers = [user5: ["user3", "user7", "user4"]]

    def grid = [user3Followers, user4Followers, user5Followers]

    assert [
      user3: ["user5"],
      user4: ["user5"],
      user5: ["user3", "user4"]
    ] == network.gridOfUsersAndFollowers(grid)

  }

  @Test
  void graphvizTest(){
    def user1Followers = [user1: ["user2", "user3", "user4"]]
    def user2Followers = [user2: ["user3", "user4", "user5"]]

    def user3Followers = [user3: ["user8", "user5", "user6"]]
    def user4Followers = [user4: ["user6", "user5", "user8"]]
    def user5Followers = [user5: ["user3", "user7", "user4"]]

    def grid = [user3Followers, user4Followers, user5Followers]

    def graphCommonFollowers = network.graphvizForFollowers(user1Followers, user2Followers)
    def graph = network.gridOfUsersAndFollowers(grid)
    println graphCommonFollowers
    println "-----"
    println network.graphvizForFollowersOfFollowers(graph)

  }
}
