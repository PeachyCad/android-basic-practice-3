package otus.gpb.homework.viewandresources

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class ContactsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        findViewById<MaterialToolbar>(R.id.toolbar).setNavigationOnClickListener { finish() }

        // filter = false, иначе при открытии списка останется только выбранный пункт
        val phoneTypes = resources.getStringArray(R.array.phone_types)
        findViewById<MaterialAutoCompleteTextView>(R.id.phone_type).setText(phoneTypes.first(), false)
    }
}
