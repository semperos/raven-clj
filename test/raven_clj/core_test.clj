(ns raven-clj.core-test
  (:use clojure.test
        raven-clj.core)
  (:import [java.sql Timestamp]
           [java.util Date]))

(deftest test-make-sentry-url
  (testing "secure url"
    (is (= (make-sentry-url "https://example.com" "1")
           "https://example.com/api/1/store/")))
  (testing "insecure url"
    (is (= (make-sentry-url "http://example.com" "1")
           "http://example.com/api/1/store/"))))

(deftest test-make-sentry-header
  (testing "sentry header"
    (let [ts (str (Timestamp. (.getTime (Date.))))]
      (is (= (make-sentry-header ts
                                 "b70a31b3510c4cf793964a185cfe1fd0"
                                 "b7d80b520139450f903720eb7991bf3d")
             (format "Sentry sentry_version=2.0, sentry_client=raven-clj/1.0, sentry_timestamp=%s, sentry_key=b70a31b3510c4cf793964a185cfe1fd0, sentry_secret=b7d80b520139450f903720eb7991bf3d" ts))))))