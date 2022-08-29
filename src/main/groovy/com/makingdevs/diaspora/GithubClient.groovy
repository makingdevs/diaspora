package com.makingdevs.diaspora

import wslite.rest.*

@Singleton
class GithubClient {

  String urlBase = "https://api.github.com"
  String API_KEY = System.getenv("GITHUB_API_KEY")
  Integer pageSize = 100

  Map getUser(String username) {
    RESTClient client = new RESTClient(urlBase)
    def response = client.get(path:"/users/$username", headers: ["Authorization" : "token $API_KEY"])
    assert 200 == response.statusCode
    response.json
  }

  List getFollowersForUser(Map user) {

    Integer pages = (user.followers / pageSize).toInteger() + (user.followers % pageSize ? 1 : 0 )

    RESTClient client = new RESTClient(urlBase)
    List followers = []
    (1..pages).step(1) { page ->
      def response = client.get(
        path:"/users/${user.login}/followers",
        headers: ["Authorization" : "token $API_KEY"],
        query: ["per_page": pageSize, page: page])
      assert 200 == response.statusCode

      followers << response.json
    }
    followers
  }
}
