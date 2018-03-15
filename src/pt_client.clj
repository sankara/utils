(ns pt-client
  (:use [config])
  (:require [clj-http.client :as http]
            [clojure.string :as str]))

(def settings
  (merge (select-keys config/config [:pt-token :pt-default-project])
         {:base-url "https://www.pivotaltracker.com/services/v5/"}))
;; settings
  
(defn- call [uri]
  (let [url (str (:base-url settings) uri)]
    (http/get url {:headers {"X-TrackerToken" (:pt-token settings)}
                   :as :json})))
  

(defn get-story [story-id & {:keys [project-id] :or {project-id (:pt-default-project settings)}}]
  (let [uri (format "/projects/%s/stories/%s" project-id story-id)]
    (:body (call uri))))

;; (get-story 155267317)

(def format-str
  "- %s
  [%s](%s)
")

(defn format-commit-message [story-id]
  (as-> story-id v
    (get-story v)
    (select-keys v [:id :name :url])
    (format format-str (:name v) (:id v) (:url v))))

;; (format-commit-message 155267317)
	

