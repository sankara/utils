(ns release-message
  (:require [pt-client]
            [clipboard]))

;; (re-find #"\d+" "infer-store-155245073")

(defn -main [& branches]
  (let [message (reduce str
                        (map #(->> %
                                   (re-find #"\d+")
                                   (pt-client/format-commit-message)) branches))]
    (println message)
    (clipboard/spit message)))


;;  (println (:body (get-story 155267317))))
;;  (-main 155267317)
