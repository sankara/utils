(ns clipboard)

(defn get []
  (.getSystemClipboard (java.awt.Toolkit/getDefaultToolkit)))

(defn slurp []
  (try
    (.getTransferData (.getContents (get) nil) (java.awt.datatransfer.DataFlavor/stringFlavor))
    (catch java.lang.NullPointerException e nil)))

(defn spit [text]
  (.setContents (get) (java.awt.datatransfer.StringSelection. text) nil))
