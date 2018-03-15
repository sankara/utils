(ns release-message
  (:require [pt-client]
            [clipboard]))

;; (re-find #"\d+" "infer-store-155245073")

(defn get-story-id [branch]
  (re-find #"\d+" branch))

(defn release-message [branches]
  (->> branches
       (map get-story-id)
       (remove nil?)
       (map pt-client/format-commit-message)
       (apply str)))

;; (def branches ["155837347-showroom-check-in" "bug-intl-shipping-155712450" "155837347-showroom-check-in" "continue-shopping" "refer-a-friend-154578392" "add-inventory-filter-153609117" "email-filter-155987367"])
;; (release-message branches)

(defn -main [& branches]
  (let [message (release-message branches)]
    (println message)
    (clipboard/spit message)))


;;  (println (:body (get-story 155267317))))
;; (-main 155837347-showroom-check-in)
