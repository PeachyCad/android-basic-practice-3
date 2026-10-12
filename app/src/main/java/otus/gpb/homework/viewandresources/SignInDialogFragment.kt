package otus.gpb.homework.viewandresources

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Диалог входа. Обёрнут в DialogFragment, чтобы переживать поворот экрана:
 * FragmentManager пересоздаёт фрагмент после смены конфигурации,
 * а введённый текст восстанавливается по id полей.
 */
class SignInDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = MaterialAlertDialogBuilder(requireContext())
        // Inflater берём из контекста билдера, а не layoutInflater фрагмента:
        // у DialogFragment он сам вызывает onCreateDialog, и получилась бы рекурсия
        val view = LayoutInflater.from(builder.context).inflate(R.layout.dialog_signin, null)

        view.findViewById<Button>(R.id.login_button).setOnClickListener { dismiss() }
        view.findViewById<Button>(R.id.cancel_button).setOnClickListener { dismiss() }

        return builder.setView(view).create()
    }

    companion object {
        const val TAG = "SignInDialog"
    }
}
