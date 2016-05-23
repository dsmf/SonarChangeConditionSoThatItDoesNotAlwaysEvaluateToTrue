
import java.io.Serializable;
import java.util.Comparator;

public class PartComparator implements Comparator<Part>, Serializable {

   private static final long serialVersionUID = 1L;

   @Override
   public int compare(final Part p1, final Part p2) {

      // null checks
      if (p1 == null && p2 == null) {
         return 0;
      } else if (p1 != null && p2 == null) {
         return 1;
      } else if (p1 == null && p2 != null) {
         return -1;
      } else {

         // null checks
         if (p1.getRecord() == null && p2.getRecord() == null) {
            return 0;
         } else if (p1.getRecord() != null && p2.getRecord() == null) {
            return 1;
         } else if (p1.getRecord() == null && p2.getRecord() != null) {
            return -1;
         } else if (p1.getRecord() != null && p2.getRecord() != null) {

            // type checks
            if (isRecordFromSap(p1) && isRecordFromSupplier(p2)) {
               return 1;
            } else if (isRecordFromSupplier(p1) && isRecordFromSap(p2)) {
               return -1;
            } else if (areRecordsOfSameType(p1, p2)) {
               // comparing same type
               final MyRecord r1 = (MyRecord) p1.getRecord();
               final MyRecord r2 = (MyRecord) p2.getRecord();
               return r1.getSourcePos().compareTo(r2.getSourcePos());
            }
         }
      }

      return 0;
   }

   private boolean areRecordsOfSameType(final Part et1, final Part et2) {
      return (isRecordFromSap(et1) && isRecordFromSap(et2)) || (isRecordFromSupplier(et1) && isRecordFromSupplier(et2));
   }

   private boolean isRecordFromSupplier(final Part et2) {
      return et2.getRecord() instanceof RecordType1;
   }

   private boolean isRecordFromSap(final Part et1) {
      return et1.getRecord() instanceof RecordType2;
   }

}
